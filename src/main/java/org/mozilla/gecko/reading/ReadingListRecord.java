/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.gecko.reading;

import org.mozilla.gecko.sync.ExtendedJSONObject;

public class ReadingListRecord {
  public final int id;
  public final long lastModified;     // A server timestamp.

  public final String url;
  public final String title;
  public final String addedBy;

  private String getDefaultAddedBy() {
    return "Test Device";                // TODO
  }

  public ReadingListRecord(ExtendedJSONObject obj) {
    // Server populated.
    this.lastModified = obj.getLong("last_modified");
    this.id = obj.getIntegerSafely("id");

    // Required fields.
    this.url = obj.getString("url");
    this.title = obj.getString("title");
    this.addedBy = obj.getString("added_by");
  }

  public ReadingListRecord(String url, String title, String addedBy) {
    this.id = -1;
    this.lastModified = -1;

    // Required.
    if (url == null) {
      throw new IllegalArgumentException("url must be provided.");
    }
    this.url = url;
    this.title = title == null ? "" : title;
    this.addedBy = addedBy == null ? getDefaultAddedBy() : addedBy;
  }

  public ExtendedJSONObject toJSON() {
    final ExtendedJSONObject object = new ExtendedJSONObject();
    if (this.id != -1) {
      object.put("id", this.id);
    }
    object.put("url", this.url);
    if (this.title != null) {
      object.put("title", this.title);
    }
    return object;
  }
}