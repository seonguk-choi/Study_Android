package com.csslect.app.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.csslect.app.command.ACommand;
import com.csslect.app.command.ADeleteMultiCommand;
import com.csslect.app.command.AInsertMultiCommand;
import com.csslect.app.command.ASelectMultiCommand;
import com.csslect.app.command.AUpdateMultiCommand;
import com.csslect.app.command.AUpdateMultiNoCommand;

@Controller
public class AController {
	
	ACommand command;
	
	@RequestMapping(value="/anSelectMulti", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anSelectMulti(HttpServletRequest req, Model model){
		System.out.println("anSelectMulti()");
		
		command = new ASelectMultiCommand();
		command.execute(model);
		
		return "anSelectMulti";
	}
	
	@RequestMapping(value="/anInsertMulti", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String anInsertMulti(HttpServletRequest req, Model model){
		System.out.println("anInsertMulti()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		
		String id = (String) req.getParameter("id");
		String name = (String) req.getParameter("name");
		String date = (String) req.getParameter("date");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String uploadtype = (String) req.getParameter("uploadType");
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(date);
		System.out.println(dbImgPath);
		System.out.println(uploadtype);
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("date", date);	
		model.addAttribute("dbImgPath", dbImgPath);	
		model.addAttribute("uploadtype", uploadtype);
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		if(uploadtype.equals("image")) {
			file = multi.getFile("image");
		}else if(uploadtype.equals("video")) {
			file = multi.getFile("video");	
		}
			
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// ???????????? ???????????? ????????? ??????
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// ???????????????, ????????? ?????? ??????
					file.transferTo(new File(realImgPath, fileName));
					
					String videoImageName = "";
					String videoImagePath = "";
					
					// ????????? ???????????? 10????????? ????????? ????????? ????????? ???????????? ??????
					if(uploadtype.equals("video")) {
						videoImageName = makeVideoToImage(req, realImgPath, fileName);
						String fileNamePath = dbImgPath.split("/")[dbImgPath.split("/").length-1];
						videoImagePath = dbImgPath.replace(fileNamePath, videoImageName);
						
						System.out.println("videoimagepath : " + videoImagePath);
						
					}else if(uploadtype.equals("image")) {
						videoImagePath = "novideo";
					}
					// model??? ????????? ????????? ????????? ??????
					model.addAttribute("videoImagePath", videoImagePath);
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
				
		command = new AInsertMultiCommand();
		command.execute(model);
		
		return "anInsertMulti";
	}
		
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}	
	}
	
	//??????????????? ??????????????? ????????? ????????????
	public String makeVideoToImage(HttpServletRequest req, String realImgPath, String fileName) {
		long time = System.currentTimeMillis();
        int frameNumber = 10;
        String videoImageName = "";
        String videoImagePath = "";
        
		try {
			File f3 = new File(req.getSession().getServletContext()
					.getRealPath("/resources"));
			if(!f3.isDirectory()){
				f3.mkdir();
			}
									
			Picture picture;
			try {
				picture = FrameGrab.getFrameFromFile(new File(realImgPath, fileName), frameNumber);
				videoImageName = fileName.replace(".", "_") + ".jpg";
				videoImagePath = f3 + "/" + videoImageName;
				System.out.println("videoimagepath1 : " + videoImagePath);
				
				BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
		    	ImageIO.write(bufferedImage, "jpg", new File(videoImagePath));
			}catch (IOException e) {				
				e.printStackTrace();
			}            
            
		} catch (JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		            
        
        System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
	
        // ????????? ????????? ???????????? ??????
        return videoImageName;
	}
	
	/*private  BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }*/
	
	@RequestMapping(value="/anUpdateMulti", method = {RequestMethod.GET, RequestMethod.POST})
	public void anUpdateMulti(HttpServletRequest req, Model model){
		System.out.println("anUpdateMulti()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		String id = (String) req.getParameter("id");
		String name = (String) req.getParameter("name");
		String date = (String) req.getParameter("date");
		String uploadtype = (String) req.getParameter("uploadType");
		String pUploadtype = (String) req.getParameter("pUploadType");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String pDbImgPath = (String) req.getParameter("pDbImgPath");
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(date);
		System.out.println(uploadtype);
		System.out.println(pUploadtype);
		System.out.println("Sub1Update:dbImgPath " + dbImgPath);
		System.out.println("Sub1Update:pDbImgPath " + pDbImgPath);
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("date", date);
		model.addAttribute("uploadtype", uploadtype);	
		model.addAttribute("dbImgPath", dbImgPath);
		
		// ???????????? ?????? ????????? ???????????? ?????? ????????? ??????????????? ??????
		if(!dbImgPath.equals(pDbImgPath)){			
			
			String pFileName = req.getParameter("pDbImgPath").split("/")[req.getParameter("pDbImgPath").split("/").length -1];
			String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/" + pFileName);
			
			File delfile = new File(delDbImgPath);
			System.out.println(delfile.getAbsolutePath());
			
	        if(delfile.exists()) {
	        	boolean deleteFile = false;
	            while(deleteFile != true){
	            	deleteFile = delfile.delete();
	            }
	            
	            if(pUploadtype.equals("video")){
	            	String videoImage = pFileName.replace(".","_") + ".jpg";
	            	System.out.println("Sub1Update:videoImgFilename : " + videoImage);
	            	
	            	String delvideoImage = req.getSession().getServletContext()
	            			.getRealPath("/resources/" + videoImage);
	            	
	            	File delfile2 = new File(delvideoImage);
	            	System.out.println("Sub1Update:delFile2 : " + delfile2.getAbsolutePath());
	            	
	            	 if(delfile2.exists()) {
	                     System.out.println("Sub1Update:delFile2 : " + delfile2.exists());
	                     boolean deleteFile2 = false;
	                     while(deleteFile2 != true){
	                    	 deleteFile2 = delfile2.delete();
	                     } 
	            	 }
	            }//if(pUploadtype.equals("video"))
	            
	        }//if(delfile.exists())
		
		}//if(!dbImgPath.equals(pDbImgPath))  
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		if(uploadtype.equals("image")) {
			file = multi.getFile("image");
		}else if(uploadtype.equals("video")) {
			file = multi.getFile("video");	
		}
			
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			
			// ???????????? ???????????? ????????? ??????
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// ???????????????, ????????? ?????? ??????
					file.transferTo(new File(realImgPath, fileName));
					
					String videoImageName = "";
					String videoImagePath = "";
					
					// ????????? ???????????? 10????????? ????????? ????????? ????????? ???????????? ??????
					if(uploadtype.equals("video")) {
						videoImageName = makeVideoToImage(req, realImgPath, fileName);
						String fileNamePath = dbImgPath.split("/")[dbImgPath.split("/").length-1];
						videoImagePath = dbImgPath.replace(fileNamePath, videoImageName);
						
						System.out.println("videoimagepath2 : " + videoImagePath);
						
					}else if(uploadtype.equals("image")) {
						videoImagePath = "novideo";
					}
					// model??? ????????? ????????? ????????? ??????
					model.addAttribute("videoImagePath", videoImagePath);
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
			
		}
		
		command = new AUpdateMultiCommand();
		command.execute(model);		
		
	}
	
	@RequestMapping(value="/anUpdateMultiNo", method = {RequestMethod.GET, RequestMethod.POST})
	public void anUpdateMultiNo(HttpServletRequest req, Model model){
		System.out.println("anUpdateMultiNo()");	
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		String id = (String) req.getParameter("id");
		String name = (String) req.getParameter("name");
		String date = (String) req.getParameter("date");		
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("date", date);
		
		command = new AUpdateMultiNoCommand();
		command.execute(model);		
		
	}
		
	
	@RequestMapping(value="/anDeleteMulti", method = {RequestMethod.GET, RequestMethod.POST})
	public void anDeleteMulti(HttpServletRequest req, Model model){
		System.out.println("anDeleteMulti()");		
		
		model.addAttribute("id", req.getParameter("id"));		
				
		System.out.println((String)req.getParameter("id"));
		System.out.println((String)req.getParameter("delDbImgPath"));
		System.out.println((String)req.getParameter("selUploadType"));
		
		
		String pFileName = req.getParameter("delDbImgPath").split("/")[req.getParameter("delDbImgPath").split("/").length -1];
		String delDbImgPath = req.getSession().getServletContext().getRealPath("/resources/" + pFileName);		
		
		// ????????? ???????????????
		File delfile = new File(delDbImgPath);
		System.out.println(delfile.getAbsolutePath());
		
        if(delfile.exists()) {
            System.out.println("Sub1Del:pDelImagePath " + delfile.exists());
            boolean deleteFile = false;
            while(deleteFile != true){
            	deleteFile = delfile.delete();
            } 
            
            if(((String)req.getParameter("selUploadType")).equals("video")){
            	String videoImage = pFileName.replace(".","_") + ".jpg";
            	System.out.println("videoImagePath : " + videoImage);
            	
            	String delvideoImage = req.getSession().getServletContext()
            			.getRealPath("/resources/" + videoImage);
            	
            	File delfile2 = new File(delvideoImage);
            	System.out.println("delFile2 : " + delfile2.getAbsolutePath());
            	
            	 if(delfile2.exists()) {
                     System.out.println("Sub1Del:pDelImagePath " + delfile2.exists());
                     boolean deleteFile2 = false;
                     while(deleteFile2 != true){
                    	 deleteFile2 = delfile2.delete();
                     } 
            	 }
            }
        }	
		
		command = new ADeleteMultiCommand();
		command.execute(model);	
		
	}

}
