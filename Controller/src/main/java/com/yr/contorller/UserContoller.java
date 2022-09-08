package com.yr.contorller;

import com.yr.entity.User;
import com.yr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserContoller {

	
	
	@Autowired
	private UserService userService;

	
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	@RequestMapping("/users")
	public String getUsers(Map<String, Object> map,Locale locale) {
		List<User> users = userService.cx();
		map.put("users", users);
		map.put("lang", locale.toString());
		return "list";
	}
	
	
	
	/**
	 * 头像显示
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/head/show" })
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) {
		// img为图片的二进制流
		InputStream inputStream;
		try {
			User user = userService.getUserById(id);
			//inputStream = new FileInputStream("D:\\springmvc\\Springmvc_spring\\WebContent\\head\\20191111113156418.jpg");
			inputStream = new FileInputStream(user.getHeadUrl());
			byte[] img = new byte[inputStream.available()];
			inputStream.read(img);
			httpServletResponse.setContentType("image/png");
			OutputStream os = httpServletResponse.getOutputStream();
			os.write(img);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	
	
	
	/**
	 * 添加
	 * @param map
	 * @return
	 */
	@RequestMapping("/tj")
	public String addUserShow(Map<String, Object> map) {
		Map<Integer, String> mapSex = new HashMap<>();
		mapSex.put(1, "男");
		mapSex.put(2, "女");

		map.put("sex", mapSex);
		map.put("user", new User());
		return "input";
	}


	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)//删除
	public String delete(@PathVariable("id") int id){
		System.out.println("111111111111111111111111111111111111111111111");
//		User user =new User();
//		user.setId(id);
		userService.delete(id);
		return "redirect:/users";
	}
	
	
	/**
	 * 修改回响
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)//修改回响
	public String updateShow(@PathVariable("id")int id,Map<String,Object> map)
	{

		User user = userService.getUserById(id);
		System.out.println(user);
		map.put("user", user);
		
		Map<Integer, String> mapSex = new HashMap<>();
		mapSex.put(1, "男");
		mapSex.put(2, "女");

		map.put("sex", mapSex);
		return "input";
	}

	
	/**
	 * 添加保存
	 * @param user
	 * @param value
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value = "/user",method=RequestMethod.POST)//添加保存
	public String addUser(@Valid User user,Errors value, @RequestParam("head") MultipartFile uploadFile) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String fileName = simpleDateFormat.format(new Date());
			String name = uploadFile.getOriginalFilename();
			String names[] = name.split("\\.");

			File file = new File("C:\\Users\\Administrator\\Desktop\\lsl" + File.separator + fileName + "."
					+ names[names.length - 1]);
			InputStream inputStream = uploadFile.getInputStream();// 获取到文件上传的流
			OutputStream outputStream = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
			outputStream.flush();
			outputStream.close();
			user.setHeadUrl(file.getPath());
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users";

		
		

	}
	
	/**
	 * id 等于空是，数据库里的id数据 补上去
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String, Object> map){
		if(id != null && id != 0){
			map.put("user", userService.getUserById(id));
		}
	}
	
	
	/**
	 * 修改保存
	 * @param user
	 * @param uploadFile
	 * @return
	 */
	
	@RequestMapping(value = "/user",method=RequestMethod.PUT)
	public String updateUser(@Valid User user, @RequestParam("head") MultipartFile uploadFile) {

		System.out.println("修改");


		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String fileName = simpleDateFormat.format(new Date());
			String name = uploadFile.getOriginalFilename();
			String names[] = name.split("\\.");
			File file = new File("C:\\Users\\Administrator\\Desktop\\lsl" + File.separator + fileName + "."
					+ names[names.length - 1]);
			InputStream inputStream = uploadFile.getInputStream();// 获取到文件上传的流

			OutputStream outputStream = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}

			outputStream.flush();
			outputStream.close();
			user.setHeadUrl(file.getPath());
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users";


	}

}
