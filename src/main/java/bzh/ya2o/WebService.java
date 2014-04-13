package bzh.ya2o;


import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("/foo")
@Singleton
@Produces(TEXT_HTML)
public class WebService {
    private final Dao<String> dao;

    @Inject
    public WebService(Dao<String> dao) {
        this.dao = dao;
    }

    @GET
    @Produces(TEXT_HTML)
    String getAll() {
        String html = "<h2>All stuff</h2><ul>";
        for (String bar : dao.getAll()) {
            html += "<li>" + bar + "</li>";
        }
        html += "</ul>";
        return html;
    }

    @GET
    @Path("{id}")
    @Produces(TEXT_HTML)
    public String getById(@PathParam("id") String id) {
        Optional<String> bar = dao.getById(id);
        if (!bar.isPresent())
            return notFound();
        else
            return "<html><body><div>" + bar.get() + "</div></body></html>";
    }

    private String notFound() {
        return "<html><body><div>Not Found</div></body></html>";
    }
}
