package cz.wz.marysidy.quest.filter;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

class GameFilterTest {

    @Test
    void SessionIsNull_ShouldRedirect() throws Exception {
        GameFilter filter = new GameFilter();
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
    void PlayerNameMissing_ShouldRedirect() throws Exception {
        GameFilter filter = new GameFilter();
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
    void QuestIdMissing_ShouldRedirect() throws Exception {
        GameFilter filter = new GameFilter();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn("John");
        when(session.getAttribute("questId")).thenReturn(null);  // questId отсутствует
        when(req.getContextPath()).thenReturn("");

        filter.doFilter(req, resp, chain);

        verify(resp).sendRedirect("/home");
        verify(chain, never()).doFilter(req, resp);
    }

    @Test
    void SessionValid_ShouldPassFilter() throws Exception {
        GameFilter filter = new GameFilter();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn("John");
        when(session.getAttribute("questId")).thenReturn("forest");
        when(session.getAttribute("currentStepId")).thenReturn("start");

        filter.doFilter(req, resp, chain);

        verify(chain).doFilter(req, resp);
    }
}
