package com.main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.main.dao.ILoginDAO;
import com.main.service.ILoginAppService;
import com.main.service.LoginAppServiceImp;

public class LoginServiceMgntTest {

	private static ILoginAppService service;
	private static ILoginDAO loginDAOMock;
	
	@BeforeAll
	public static void createOnce()
	{
		 loginDAOMock = Mockito.mock(ILoginDAO.class);
		 service = new LoginAppServiceImp(loginDAOMock);
	}
	
	@Test
	public void testLoginWithValidCredential()
	{
		//provide stub(Temporary functionality) for DAO's authenticate method
		Mockito.when(loginDAOMock.authenticateUser("raja", "rani")).thenReturn(true);
		//Junit Test
		assertTrue(service.login("raja", "rani"));
		
	}
	@Test
	public void testLoginWithInvalidValidCredential()
	{
		//provide stub(Temporary functionality) for DAO's authenticate method
		Mockito.when(loginDAOMock.authenticateUser("raja", "rani1")).thenReturn(false);
		//Junit Test
		assertFalse(service.login("raja", "rani1"));
		
	}
	
	@Test
	public void testLoginWithNoCredential()
	{
		 assertThrows(IllegalArgumentException.class, ()->{
			 service.login("", "");
		 });
	}
	@Test
	public void testRegisterWithSpy()
	{
		ILoginDAO dao = Mockito.spy(ILoginDAO.class);
		ILoginAppService service = new LoginAppServiceImp(dao);
		service.registerUser("sukanta", "admin");
		service.registerUser("mukti", "visitor");
		service.registerUser("manoj", "");
		
		Mockito.verify(dao,Mockito.times(1)).addUser("sukanta", "admin");
		//Mockito.verify(dao,Mockito.times(0)).addUser("mukti", "visitor");
		//Mockito.verify(dao,Mockito.never()).addUser("manoj", "");
	}
	@AfterAll
	public static void closeOnce()
	{
		service = null;
		loginDAOMock = null;
	}
}
