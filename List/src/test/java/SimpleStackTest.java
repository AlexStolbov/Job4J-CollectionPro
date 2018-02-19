import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenLastInputThenFirstOut() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        int element = 0;
        stack.push(element++);
        stack.push(element++);
        stack.push(element);

        assertThat(stack.poll(), is(element--));
        assertThat(stack.poll(), is(element--));
        assertThat(stack.poll(), is(element));
    }

    @Test
    public void whenPolThenStackDeleteElement() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.poll(), is(1));
        stack.push(2);
        assertThat(stack.poll(), is(2));
        assertNull(stack.poll());
    }

}