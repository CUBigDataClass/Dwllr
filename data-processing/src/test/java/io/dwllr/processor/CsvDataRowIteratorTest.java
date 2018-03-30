package io.dwllr.processor;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.List;
import java.util.Optional;


public class CsvDataRowIteratorTest {

    @Test
    public void basicFileTest() {
        try {
            DataRowIterator iterator = new CsvDataRowIterator(getResourceUri("basictest.csv"));

            Optional<List<String>> firstLineOptional = iterator.getNextRow();
            if (!firstLineOptional.isPresent()) {
                Assert.fail("Line 1 not read");
            }
            Assert.assertEquals("test data 1", firstLineOptional.get().get(0));
            Assert.assertEquals("test data 2", firstLineOptional.get().get(1));

            Optional<List<String>> secondLineOptional = iterator.getNextRow();
            if (!secondLineOptional.isPresent()) {
                Assert.fail("Line 2 not read");
            }
            Assert.assertEquals("test data 3", secondLineOptional.get().get(0));
            Assert.assertEquals("test data 4", secondLineOptional.get().get(1));

            Optional<List<String>> emptyOptional = iterator.getNextRow();
            Assert.assertTrue(!emptyOptional.isPresent());

            iterator.closeDataset();

        } catch (Exception e) {
            Assert.fail("Caught Exception: " + e.getMessage());
        }
    }

    @Test
    public void fileDoesNotExist() {
        try {
            DataRowIterator iterator = new CsvDataRowIterator("filedoesnotexistfdsafdsa");

        } catch (DatasetException e) {
            return;
        }

        Assert.fail("No exception thrown");
    }

    private String getResourceUri(String filename) {
        URL url = this.getClass().getResource("/csv/" + filename);
        return url.getFile();
    }
}
