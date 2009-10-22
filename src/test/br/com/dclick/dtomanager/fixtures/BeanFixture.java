package br.com.dclick.dtomanager.fixtures;

import br.com.dclick.dtomanager.annotations.Alias;

public class BeanFixture {
	public BeanFixture(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	private Long id;
	@Alias("user")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
