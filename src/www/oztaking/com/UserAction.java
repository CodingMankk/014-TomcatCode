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
	 * [1]����post�ύ���
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

		// ��2�����յ�app����󷵻�״̬
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.write("Server:postString Success!");
		writer.flush();

		return null;

	}

	private String username;
	private String password;
	private String paramskey1;
	private String paramskey2;
	private String paramskey3;




	/**
	 * [2]get ��������
	 * 
	 * @return
	 * @throws IOException
	 */
	public String login() throws IOException {
		// ��1�������յ���get��ݴ�ӡ�ڿ���̨
		System.out.println(username + "," + password);
		System.out.println(paramskey1 + "," + paramskey2+"," + paramskey3);
		// ��2�����յ�app����󷵻�״̬
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
	 * [3]postFile --���տͻ����ύ��file-�˴���һ��Image��
	 * 
	 * �ļ������λ�ã�apache-tomcat-7.0.54\webapps\okhttpDemo\files
	 * 
	 * @throws IOException
	 */

	public String postFile() throws IOException {
		
		String fileName = "struts-2.3.34-all.zip";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletInputStream is = request.getInputStream();

		String dir = ServletActionContext.getServletContext().getRealPath(
				"files");
		File file = new File(dir, fileName); // ���ļ�����Ϊ����Ƶ��ļ���
		FileOutputStream fos = new FileOutputStream(file);

		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = is.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}

		fos.flush();
		fos.close();

		// ��2�����յ�app����󷵻�״̬
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = response.getWriter();
		writer.write("Server: postFile Success!");
		writer.flush();

		return null;
	}
	
	
	public String getParamskey1() {
		return paramskey1;
	}

	public void setParamskey1(String paramskey1) {
		this.paramskey1 = paramskey1;
	}

	public String getParamskey2() {
		return paramskey2;
	}

	public void setParamskey2(String paramskey2) {
		this.paramskey2 = paramskey2;
	}

	public String getParamskey3() {
		return paramskey3;
	}

	public void setParamskey3(String paramskey3) {
		this.paramskey3 = paramskey3;
	}

}
