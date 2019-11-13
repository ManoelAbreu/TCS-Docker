package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Perfil;
import model.Usuario;

@WebFilter(filterName = "FiltroAutorizacao", urlPatterns = {"*.xhtml"})
public class FiltroAutorizacao implements Filter {

    private Usuario usuario;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);

        String reqURI = req.getRequestURI();
        if (session != null) {
            usuario = (Usuario) session.getAttribute("usuarioLogado");
        }

        if (reqURI.contains("javax.faces.resource") || reqURI.endsWith("/login.xhtml") || usuario != null) {
            if (usuario != null && usuario.getPerfil() == Perfil.GERENTE) {
                chain.doFilter(request, response);
            } else if (usuario != null && usuario.getPerfil() == Perfil.COZINHA && (reqURI.contains("cadastro") || reqURI.contains("pesquisa") || reqURI.contains("caixa"))) {
                res.sendRedirect(req.getContextPath() + "/cozinha/pedido-cozinha.xhtml");
            } else if (usuario != null && usuario.getPerfil() == Perfil.CAIXA && (reqURI.contains("cadastro") || reqURI.contains("pesquisa") || reqURI.contains("garcom") || reqURI.contains("cozinha"))) {
                if (reqURI.contains("garcom") && reqURI.contains("cozinha")) {
                    res.sendRedirect(req.getContextPath() + "/caixa/caixa-pedido.xhtml");
                } else if ((reqURI.contains("mesa") || reqURI.contains("pesquisa-pedido"))) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect(req.getContextPath() + "/caixa/caixa-pedido.xhtml");
                }
            } else if (usuario != null && usuario.getPerfil() == Perfil.GARCOM && (reqURI.contains("pesquisa") || reqURI.contains("cadastro") || reqURI.contains("caixa") || reqURI.contains("cozinha"))) {
                if (reqURI.contains("pesquisa-pedido") ||reqURI.contains("cadastro-pedido") || reqURI.contains("garcom")) {
                    chain.doFilter(request, response);
                }else{
                    res.sendRedirect(req.getContextPath() + "/garcom/pedido-garcom.xhtml");
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");

        }

    }

    @Override
    public void destroy() {
    }

}
