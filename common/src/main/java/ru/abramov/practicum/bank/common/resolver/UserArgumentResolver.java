package ru.abramov.practicum.bank.common.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.abramov.practicum.bank.common.annotation.CurrentUser;
import ru.abramov.practicum.bank.common.model.User;

import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)
                && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User principal)) {
            return null;
        }

        Map<String, Object> attributes = principal.getAttributes();

        User user = new User();

        user.setId((String) attributes.get("sub"));
        user.setLogin((String) attributes.get("preferred_username"));
        user.setEmail((String) attributes.get("email"));
        user.setName((String) attributes.get("given_name"));
        user.setFamilyName((String) attributes.get("family_name"));
        user.setBirthDate(LocalDate.parse((String) attributes.get("birth_date")));

        return user;
    }
}
