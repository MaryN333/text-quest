package cz.wz.marysidy.quest.filter;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.http.*;

import static org.mockito.Mockito.*;

class StartFilterTest {
    @Test
    void sessionIsNull_ShouldRedirect() throws Exception {
        StartFilter filter = new StartFilter();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(null);
        when(req.getContextPath()).thenReturn("");

        filter.doFilter(req, resp, chain);

        verify(resp).sendRedirect("/home");
        verify(chain, never()).doFilter(req, resp);
    }

    @Test
    void playerNameMissing_ShouldRedirect() throws Exception {
        StartFilter filter = new StartFilter();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");

        filter.doFilter(req, resp, chain);

        verify(resp).sendRedirect("/home");
        verify(chain, never()).doFilter(req, resp);
    }

    @Test
    void questIdMissing_ShouldRedirect() throws Exception {
        StartFilter filter = new StartFilter();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn("John");
        when(req.getParameter("questId")).thenReturn(null);
        when(req.getContextPath()).thenReturn("");

        filter.doFilter(req, resp, chain);

        verify(resp).sendRedirect("/home");
        verify(chain, never()).doFilter(req, resp);
    }

    @Test
    void sessionValid_ShouldPassFilter() throws Exception {
        StartFilter filter = new StartFilter();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn("John");
        when(req.getParameter("questId")).thenReturn("quest1");

        filter.doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }
}
