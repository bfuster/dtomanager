package br.com.dclick.dtomanager.handlers;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.fixtures.ComplexDTOFixture;
import br.com.dclick.dtomanager.fixtures.ComplexFixture;
import br.com.dclick.dtomanager.fixtures.NestedDTOFixture;
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
		first.setList( Arrays.asList( new NestedFixture( 2l ), new NestedFixture( 3l ) ) );

		/* copy */
		ComplexDTOFixture dto = new DataTransferObjectManager().copy( ComplexDTOFixture.class, first );

		assertEquals( first.getValue(), dto.getAnother() );
		assertEquals( dto.getIgnore(), null );
		assertEquals( first.getNested().getId(), dto.getNested().getId() );
		assertEquals( first.getList().size(), dto.getList().size() );
		/* list check */
		assertEquals( ( (NestedFixture) first.getList().get( 0 ) ).getId(), ( (NestedDTOFixture) dto.getList().get( 0 ) ).getId() );
		assertEquals( ( (NestedFixture) first.getList().get( 1 ) ).getId(), ( (NestedDTOFixture) dto.getList().get( 1 ) ).getId() );
	}
}
