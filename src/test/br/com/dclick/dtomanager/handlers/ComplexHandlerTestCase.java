package br.com.dclick.dtomanager.handlers;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.fixtures.ComplexDTOFixture;
import br.com.dclick.dtomanager.fixtures.ComplexFixture;
import br.com.dclick.dtomanager.fixtures.NestedFixture;

/**
 * Complex object test case
 * 
 * @author bfuster
 * 
 */
public class ComplexHandlerTestCase extends TestCase {

	@Test
	public void testComplexCopy() {

		/* fixture */
		ComplexFixture first = new ComplexFixture();
		first.setValue( "fuster" );
		first.setIgnore( "ignored" );
		first.setNested( new NestedFixture( 1l ) );

		Set< NestedFixture > collection = new HashSet< NestedFixture >();
		collection.add( new NestedFixture( 2l ) );
		collection.add( new NestedFixture( 3l ) );
		first.setList( collection );

		/* copy */
		ComplexDTOFixture dto = new DataTransferObjectManager().copy( ComplexDTOFixture.class, first );

		assertEquals( first.getValue(), dto.getAnother() );
		assertEquals( dto.getIgnore(), null );
		assertEquals( first.getNested().getId(), dto.getNested().getId() );
		assertEquals( first.getList().size(), dto.getList().size() );
	}
}
