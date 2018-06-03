package br.com.alura.home.reflection.model;

public class Usuario {
	
	private String login;
	private String senha;
	private String email;
	private String papel;
	private Boolean ativo;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public boolean validarEmail() {
		return this.email.contains("@");
	}
	
	public boolean validarSenha() {
		return this.senha.length() > 8;
	}

}
