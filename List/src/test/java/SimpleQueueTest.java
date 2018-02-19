import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenAddElementFirstThenGetItFirst() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        for (int i = 0; i < 10; i++) {
            int res = queue.poll();
            assertThat(res, is(i));
        }
    }

    @Test
    public void whenPollElementThenQueueDeleteIt() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.poll(), is(1));
        queue.push(2);
        assertThat(queue.poll(), is(2));
        queue.push(3);
        assertThat(queue.poll(), is(3));
        assertNull(queue.poll());
    }

}