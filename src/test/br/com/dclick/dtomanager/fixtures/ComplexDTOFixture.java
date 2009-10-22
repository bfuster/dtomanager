/**
 * 
 */
package br.com.dclick.dtomanager.fixtures;

import java.util.List;

import br.com.dclick.dtomanager.annotations.Alias;
import br.com.dclick.dtomanager.annotations.Composition;
import br.com.dclick.dtomanager.annotations.Ignore;
import br.com.dclick.dtomanager.annotations.Listing;

/**
 * All problems dto
 * 
 * @author bfuster
 * 
 */
public class ComplexDTOFixture {

	final String something = "some";

	@Alias( "value" )
	private String another;

	@Ignore
	private String ignore;

	@Composition
	private NestedDTOFixture nested;

	@Listing( NestedDTOFixture.class )
	private List< NestedDTOFixture > list;

	/* getters n' setters */

	public void setAnother( String another ) {

		this.another = another;
	}

	public String getAnother() {

		return another;
	}

	public String getIgnore() {

		return ignore;
	}

	public void setIgnore( String ignore ) {

		this.ignore = ignore;
	}

	public NestedDTOFixture getNested() {

		return nested;
	}

	public void setNested( NestedDTOFixture nested ) {

		this.nested = nested;
	}

	public List< NestedDTOFixture > getList() {

		return list;
	}

	public void setList( List< NestedDTOFixture > list ) {

		this.list = list;
	}

}
