package com.example.demo.security;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//����� ������ �޾� JWT�� �����ϴ� Ŭ����
public class TokenProvider {
	private static final String SECRET_KEY = "NMA8JPctFuna59f5";
	
	// JWT ��ū�� �����ϴ� �޼ҵ�
	public String create(UserEntity userEntity) {
		// ������ ���ݺ��� 1�Ϸ� ����
		Date expiryDate = Date.from(
			Instant.now().plus(1, ChronoUnit.DAYS));

		//JWT Token ����
		return Jwts.builder()
			//header�� �� ���� �� ������ �ϱ� ���� SECRET_KEY
			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
			//payload�� �� ����
			.setSubject(userEntity.getId())
			.setIssuer("demo app")
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.compact();
	}
	
	// ��ū�� ���ڵ� �� �Ľ��ϰ� ��ū�� ���� ���� Ȯ��
	public String validateAndGetUserId(String token) {
		//parseClaimsJws �޼��尡 Base64ȣ ���ڵ� �� �Ľ�
		//����� ���̷ε带 setSigningKey�� �Ѿ�� ��ũ���� �̿��� ������ �� token�� ����� ��
		//�������� �ʾҴٸ� ���̷ε�(Claims)�� ����, ������� ���ܸ� ����
		//�� �� userId�� �ʿ��ϹǷ� getBody�� �θ���.
		log.info(token);
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
}
