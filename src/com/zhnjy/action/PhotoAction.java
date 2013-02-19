package com.zhnjy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

public class PhotoAction {
	private File photoFile;// 封装上传的存照
	private File idCardFile;// 封装上传的身份证图片
	// 以学生身份证号码做文件名。
	private String idCard;
	// 这个是图片要存储的文件夹路径
	private String path;

	private String photoFileName;
	private String idCardFileName;

	public String upload() throws FileNotFoundException, Exception {

		photoFileName = this.getPhotoName();
		idCardFileName = this.getIdCardeFileName();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		boolean bool1 = false;
		boolean bool2 = false;
		if (photoFile != null) {
			fos = new FileOutputStream(getPath() + "\\" + photoFileName);
			fis = new FileInputStream(photoFile);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = fis.read(b)) > 0) {
				fos.write(b, 0, length);
			}
			bool1 = true;
			fos.close();
			fis.close();

		} else {
			bool1 = true;
		}

		if (idCardFile != null) {
			fos = new FileOutputStream(getPath() + "\\" + idCardFileName);
			fis = new FileInputStream(idCardFile);
			byte[] be = new byte[1024];
			int len = 0;
			while ((len = fis.read(be)) > 0) {
				fos.write(be, 0, len);
			}
			bool2 = true;
			fos.close();
			fis.close();
		} else {
			bool2 = true;
		}
		if (bool1 == false || bool2 == false) {
			return "uploadfail";
		} else {
			return "uploadsuccess";
		}

	}

	// 得到存照存储名字
	private String getPhotoName() {
		return "p" + idCard + ".jpg";
	}

	// 得到身份证扫面照片存储名字
	private String getIdCardeFileName() {
		return "id" + idCard + ".jpg";
	}

	// set get 方法。
	public String getPath() throws Exception {
		return ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath(path);
	}

	public void setPath(String value) {

		this.path = value;
	}

	public File getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(File photoFile) {
		this.photoFile = photoFile;
	}

	public File getIdCardFile() {
		return idCardFile;
	}

	public void setIdCardFile(File idCardFile) {
		this.idCardFile = idCardFile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getIdCardFileName() {
		return idCardFileName;
	}

	public void setIdCardFileName(String idCardFileName) {
		this.idCardFileName = idCardFileName;
	}

}
