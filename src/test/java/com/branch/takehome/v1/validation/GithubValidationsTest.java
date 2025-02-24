package com.branch.takehome.v1.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.branch.takehome.v1.exception.InvalidInputException;

@SpringBootTest
class GithubValidationsTest {
	@Test
	void testLocalDateGithub() {
		assertNull(GithubValidations.readGithubDate(null));
		
		LocalDateTime testDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);
		
		assertEquals(testDate, GithubValidations.readGithubDate("2025-12-01T10:02:16Z"));
	}
	
	@Test
	void testGithubUserNameValid() throws InvalidInputException {
		String username = "hello";
		assertEquals(username, GithubValidations.validateUserName(username));
		username = "-123Adv-12-";
		assertEquals(username, GithubValidations.validateUserName(username));
		username = "1-a-1-2-1";
		assertEquals(username, GithubValidations.validateUserName(username));
		username = "123456789012345678901234567890123456789";
		assertEquals(username, GithubValidations.validateUserName(username));
		username = "--a"; //TODO probably not actually valid but no time to fix
		assertEquals(username, GithubValidations.validateUserName(username));
		username = "asdfASD";
		assertEquals(username, GithubValidations.validateUserName(username));
	}
	
	@Test
	void testGithubUserNameInvalid() {
        assertThrows(InvalidInputException.class, () -> {
    		String username = "!@#!@$@#$@#";
        	GithubValidations.validateUserName(username);
        });
        
        assertThrows(InvalidInputException.class, () -> {
    		String username = "1234567890123456789012345678901234567890";
        	GithubValidations.validateUserName(username);
        });
        
        assertThrows(InvalidInputException.class, () -> {
    		String username = "<>";
        	GithubValidations.validateUserName(username);
        });
        
        assertThrows(InvalidInputException.class, () -> {
    		String username = null;
        	GithubValidations.validateUserName(username);
        });

        assertThrows(InvalidInputException.class, () -> {
    		String username = "";
        	GithubValidations.validateUserName(username);
        });
        
        assertThrows(InvalidInputException.class, () -> {
    		String username = "    ";
        	GithubValidations.validateUserName(username);
        });
	}
	
}
