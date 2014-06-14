package org.korsakow.services.encoders.image;

/**
 * A non-comprehensive list of codecs. In a practical sense this should only cover formats we need to single out. 
 * @author d
 *
 */
public enum ImageFormat
{
	PNG("png"),
	JPG("jpg"),
	;
	
	private String fileExtension;
	ImageFormat(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public String getFileExtension() { return fileExtension; }
}
