package com.codex.kosiea.config.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ExternalFiles {

	// 외부 css 파일 경로
	@Value("${files.external.css}")
	private String externalCssPath;
	@Value("${files.internal.css}")
	private String internalCssPath;

	@Value("${files.external.js}")
	private String externalJsPath;
	@Value("${files.internal.js}")
	private String internalJsPath;

	@Value("${files.external.media}")
	private String externalMediaPath;
	@Value("${files.internal.media}")
	private String internalMediaPath;

	@Value("${files.external.plugins}")
	private String externalPluginsPath;
	@Value("${files.internal.plugins}")
	private String internalPluginsPath;

	@Value("${files.external.templates}")
	private String externalTemplatesPath;
	@Value("${files.internal.templates}")
	private String internalTemplatesPath;
	
	@Value("${files.external.bc}")
	private String externalBc;
	@Value("${files.internal.bc}")
	private String internalBc;

	@Value("${files.external.profile}")
	private String externalProfile;
	@Value("${files.internal.profile}")
	private String internalProfile;

//	@Value("${files.external.html}")
//	private String externalHtmlPath;
//	@Value("${files.internal.html}")
//	private String internalHtmlPath;

//	@Value("${files.external.fonts}")
//	private String externalFontsPath;
//	@Value("${files.internal.fonts}")
//	private String internalFontsPath;

//	@Value("${files.external.module}")
//	private String externalModulePath;
//	@Value("${files.internal.module}")
//	private String internalModulePath;

//	@Value("${files.external.images}")
//	private String externalImagesPath;
//	@Value("${files.internal.images}")
//	private String internalImagesPath;
}
