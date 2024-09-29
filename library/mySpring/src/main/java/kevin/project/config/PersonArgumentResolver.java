package kevin.project.config;

import kevin.project.model.Person;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
public class PersonArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Person.class.equals(parameter.getParameterType());

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,

                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Person person = new Person();
        person.setName(webRequest.getParameter("name"));
        person.setAge(webRequest.getParameter("age") != null ? Integer.parseInt(webRequest.getParameter("age")) : null);

        Map<String, String[]> parameterMap = webRequest.getParameterMap();

        Map<String, String> params = person.getParams();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (!entry.getKey()
                      .equals("name") && !entry.getKey()
                                               .equals("age")) {
                params.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        return person;
    }
}