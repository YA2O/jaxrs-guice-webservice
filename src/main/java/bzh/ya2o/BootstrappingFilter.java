package bzh.ya2o;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.annotation.*;

// No need for web.xml!
@WebFilter("/*")
public class BootstrappingFilter extends GuiceFilter {}
