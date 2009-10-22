package br.com.dclick.dtomanager.handlers;

import org.junit.Assert;
import org.junit.Test;

import br.com.dclick.dtomanager.DataTransferObjectManager;
import br.com.dclick.dtomanager.fixtures.BeanDTOFixture;
import br.com.dclick.dtomanager.fixtures.BeanFixture;

public class CommonHandlerTestCase {
	@Test
	public void testCopyDTO() {
		BeanFixture beanFixture = new BeanFixture(1l, "David Paniz");
		BeanDTOFixture copy = new DataTransferObjectManager().copy(BeanDTOFixture.class, beanFixture);
		Assert.assertEquals(beanFixture.getName(), copy.getUser());
		Assert.assertEquals(beanFixture.getId(), copy.getId());
	}
}
