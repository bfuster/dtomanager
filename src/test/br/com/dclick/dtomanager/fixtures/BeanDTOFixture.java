package br.com.dclick.dtomanager.fixtures;

import br.com.dclick.dtomanager.annotations.Alias;

public class BeanDTOFixture {
	
	private Long id;
	@Alias("name")
	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}
}
