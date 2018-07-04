package www.oztaking.com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.HttpServer;

public class UserAction extends ActionSupport {

	/**
	 * [1]接收post提交数据
	 * 
	 * @throws IOException
	 */

	public String postString() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();

		StringBuilder sbBuilder = new StringBuilder();
		int len = 0;
		byte[] buf = new byte[1024];

		while ((len = is.read(buf)) != -1) {
			sbBuilder.append(new String(buf, 0, len));
		}

		System.out.println(sbBuilder.toString());

		// 【2】接收到app请求后返回状态
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.write("Server:postString Success!");
		writer.flush();

		return null;

	}

	private String username;
	private String password;

	/**
	 * [2]get 请求的数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public String login() throws IOException {
		// 【1】将接收到的get数据打印在控制台
		System.out.println(username + "," + password);
		// 【2】接收到app请求后返回状态
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.write("Server:GetRequest Success!");
		writer.flush();

		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * [3]postFile --接收客户端提交的file-此处是一张Image；
	 * 
	 * 文件保存的位置：apache-tomcat-7.0.54\webapps\okhttpDemo\files
	 * 
	 * @throws IOException
	 */

	public String postFile() throws IOException {
		
		String fileName = "360RootSetup.exe";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();

		String dir = ServletActionContext.getServletContext().getRealPath(
				"files");
		File file = new File(dir, fileName); // 将文件保存为该名称的文件；
		FileOutputStream fos = new FileOutputStream(file);

		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = is.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}

		fos.flush();
		fos.close();

		// 【2】接收到app请求后返回状态
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.write("Server: postFile Success!");
		writer.flush();

		return null;
	}

}
