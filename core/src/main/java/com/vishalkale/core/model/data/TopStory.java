package com.vishalkale.core.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStory {

    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("subsection")
    @Expose
    public String subsection;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("byline")
    @Expose
    public String byline;
    @SerializedName("item_type")
    @Expose
    public String itemType;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    public String materialTypeFacet;
    @SerializedName("kicker")
    @Expose
    public String kicker;
    @SerializedName("des_facet")
    @Expose
    public List<Object> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    public List<Object> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    public List<Object> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    public List<Object> geoFacet = null;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedia> multimedia = null;
    @SerializedName("short_url")
    @Expose
    public String shortUrl;

}