package android.example.exercise3;

import java.util.Date;

public class NewsItem_Java {

  private final String title;
  private final String imageUrl;
  private final Category_Java category;
  private final Date publishDate;
  private final String previewText;
  private final String fullText;

  public NewsItem_Java(String title, String imageUrl, Category_Java category, Date publishDate, String previewText, String fullText) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.category = category;
    this.publishDate = publishDate;
    this.previewText = previewText;
    this.fullText = fullText;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Category_Java getCategory() {
    return category;
  }

  public Date getPublishDate() {
    return publishDate;
  }

  public String getPreviewText() {
    return previewText;
  }

  public String getFullText() {
    return fullText;
  }
}
