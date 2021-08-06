package com.example.springbootjenkins;

import static org.springframework.data.jpa.domain.Specification.where;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.starai.api.contentdelivery.entity.CMSDetails;
import com.starai.api.contentdelivery.enums.CMSDetailsType;

public class ContentDetailsSpecification {

	private static final String CMS_DETAIL_TYPE = "type";

	private static final String HEADER = "header";

	private static final String SUB_HEADER = "subHeader";

	private static final String LOCALE = "locale";

	private ContentDetailsSpecification() {

	}

	public static Specification<CMSDetails> getCMSDetails(CMSDetailsType cmsDetailType, String header, String subHeader,
			String locale) {
		return (root, query, criteriaBuilder) -> where(cmsDetailTypeEquals(cmsDetailType)).and(headerEquals(header))
				.and(subHeaderEquals(subHeader)).and(localeEquals(locale)).toPredicate(root, query, criteriaBuilder);

	}

	private static Specification<CMSDetails> cmsDetailTypeEquals(CMSDetailsType cmsDetailType) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(CMS_DETAIL_TYPE), cmsDetailType);
	}

	private static Specification<CMSDetails> headerEquals(String header) {
		return getData(HEADER, header);
	}

	private static Specification<CMSDetails> subHeaderEquals(String subHeader) {
		return getData(SUB_HEADER, subHeader);
	}

	private static Specification<CMSDetails> localeEquals(String locale) {
		return getData(LOCALE, locale);
	}

	private static Specification<CMSDetails> getData(String column, String value) {
		return (root, query, criteriaBuilder) -> {
			if (!StringUtils.hasLength(value)) {
				return null;
			}
			return criteriaBuilder.equal(root.get(column), value);
		};

	}

}
