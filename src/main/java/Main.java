import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @author Felipe Soler
 * @date 27/01/2018
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        Context context = new Context();
        context.setVariable("obj", new TestBean("name", "url", "tag_1,tag_2,tag_3".split(",")));

        String result = main.textTemplateEngine().process("template_1", context);
        System.out.println(result);
    }

    public TemplateEngine textTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addTemplateResolver(textTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver textTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF8");
        templateResolver.setCheckExistence(true);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private static class TestBean {
        private String name;
        private String url;
        private String[] tags;

        public TestBean(String name, String url, String[] tags) {
            this.name = name;
            this.url = url;
            this.tags = tags;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        public String[] getTags() {
            return tags;
        }
    }
}
