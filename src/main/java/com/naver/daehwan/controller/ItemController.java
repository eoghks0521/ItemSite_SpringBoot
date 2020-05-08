package com.naver.daehwan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.daehwan.model.Item;
import com.naver.daehwan.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;

	@Value("${upload.path}")
	String uploadPath;

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerForm(Model model) {
		model.addAttribute(new Item());

		return "item/register";
	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Item item, RedirectAttributes rttr) throws Exception {
		MultipartFile pictureFile = item.getPicture();
		MultipartFile previewFile = item.getPreview();

		String createPictureFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());

		item.setPictureUrl(createPictureFilename);
		item.setPreviewUrl(createdPreviewFilename);

		itemService.register(item);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/item/list";
	}

	@GetMapping("/list")
	public void list(Model model) throws Exception {
		List<Item> itemList = itemService.list();

		model.addAttribute("itemList", itemList);
	}

	@GetMapping("/read")
	public String read(Long itemId, Model model) throws Exception {
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/read";
	}

	@GetMapping("/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modifyForm(Long itemId, Model model) throws Exception {
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/modify";
	}

	@PostMapping("/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Item item, RedirectAttributes rttr) throws Exception {
		MultipartFile pictureFile = item.getPicture();
		if (pictureFile != null && pictureFile.getSize() > 0) {
			String createdFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
			item.setPictureUrl(createdFilename);
		}
		MultipartFile previewFile = item.getPreview();
		if (previewFile != null && previewFile.getSize() > 0) {
			String createdFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
			item.setPreviewUrl(createdFilename);
		}
		itemService.modify(item);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/item/list";
	}

	@GetMapping("/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String removeForm(Long itemId, Model model) throws Exception {
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/remove";
	}

	@PostMapping("/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(Item item, RedirectAttributes rttr) throws Exception {
		itemService.remove(item.getItemId());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/item/list";
	}

	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String createdFileName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, createdFileName);
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}

	@ResponseBody
	@GetMapping("/display")
	public ResponseEntity<byte[]> displayFile(Long itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String fileName = itemService.getPreview(itemId);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			MediaType mType = getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + File.separator + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	MediaType getMediaType(String formatName) {
		if (formatName != null) {
			switch (formatName) {
				case "JPG":
					return MediaType.IMAGE_JPEG;
				case "GIF":
					return MediaType.IMAGE_GIF;
				case "PNG":
					return MediaType.IMAGE_PNG;
				default:
					break;
			}
		}
		return null;
	}

}
