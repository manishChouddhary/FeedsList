package com.synerzip.feeds

import com.google.gson.Gson
import com.synerzip.feeds.model.FeedsResponse
import com.synerzip.feeds.model.ImEntity

object MockProvider {
    const val response ="""{
  "feed": {
    "author": {
      "name": {
        "label": "iTunes Store"
      },
      "uri": {
        "label": "http://www.apple.com/itunes/"
      }
    },
    "entry": [{
      "im:name": {
        "label": "AON CySec"
      },
      "rights": {
        "label": "© Cyan Security Group GmbH"
      },
      "im:price": {
        "label": "Get",
        "attributes": {
          "amount": "0.00000",
          "currency": "USD"
        }
      },
      "im:image": [
        {
          "label": "https://is4-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/53x53bb.png",
          "attributes": {
            "height": "53"
          }
        },
        {
          "label": "https://is5-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/75x75bb.png",
          "attributes": {
            "height": "75"
          }
        },
        {
          "label": "https://is4-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/100x100bb.png",
          "attributes": {
            "height": "100"
          }
        }
      ],
      "im:artist": {
        "label": "cyan Security Group GmbH",
        "attributes": {
          "href": "https://apps.apple.com/us/developer/cyan-security-group-gmbh/id1484041676?uo=2"
        }
      },
      "title": {
        "label": "AON CySec - cyan Security Group GmbH"
      },
      "link": {
        "attributes": {
          "rel": "alternate",
          "type": "text/html",
          "href": "https://apps.apple.com/us/app/aon-cysec/id1498892428?uo=2"
        }
      },
      "id": {
        "label": "https://apps.apple.com/us/app/aon-cysec/id1498892428?uo=2",
        "attributes": {
          "im:id": "1498892428",
          "im:bundleId": "com.aon.ios.eps"
        }
      },
      "im:contentType": {
        "attributes": {
          "term": "Application",
          "label": "Application"
        }
      },
      "category": {
        "attributes": {
          "im:id": "6002",
          "term": "Utilities",
          "scheme": "https://apps.apple.com/us/genre/ios-utilities/id6002?uo=2",
          "label": "Utilities"
        }
      },
      "im:releaseDate": {
        "label": "2020-04-30T00:00:00-07:00",
        "attributes": {
          "label": "April 30, 2020"
        }
      }
    }],
    "updated": {
      "label": "2020-04-30T07:34:21-07:00"
    },
    "rights": {
      "label": "Copyright 2008 Apple Inc."
    },
    "title": {
      "label": "iTunes Store: New Free Applications"
    },
    "icon": {
      "label": "http://itunes.apple.com/favicon.ico"
    },
    "link": [
      {
        "attributes": {
          "rel": "alternate",
          "type": "text/html",
          "href": "https://itunes.apple.com/WebObjects/MZStore.woa/wa/storeFront?cc=us"
        }
      },
      {
        "attributes": {
          "rel": "self",
          "href": "https://itunes.apple.com/us/rss/newfreeapplications/limit=2/json"
        }
      }
    ],
    "id": {
      "label": "https://itunes.apple.com/us/rss/newfreeapplications/limit=2/json"
    }
  }
}"""

    const val title = "iTunes Store"

    const val entity = """{
      "im:name": {
        "label": "AON CySec"
      },
      "rights": {
        "label": "© Cyan Security Group GmbH"
      },
      "im:price": {
        "label": "Get",
        "attributes": {
          "amount": "0.00000",
          "currency": "USD"
        }
      },
      "im:image": [
        {
          "label": "https://is4-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/53x53bb.png",
          "attributes": {
            "height": "53"
          }
        },
        {
          "label": "https://is5-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/75x75bb.png",
          "attributes": {
            "height": "75"
          }
        },
        {
          "label": "https://is4-ssl.mzstatic.com/image/thumb/Purple113/v4/07/12/e7/0712e7e1-9346-8dca-acf1-c9227097b4d0/AppIcon-0-0-1x_U007emarketing-0-0-0-7-0-0-sRGB-0-0-0-GLES2_U002c0-512MB-85-220-0-0.png/100x100bb.png",
          "attributes": {
            "height": "100"
          }
        }
      ],
      "im:artist": {
        "label": "cyan Security Group GmbH",
        "attributes": {
          "href": "https://apps.apple.com/us/developer/cyan-security-group-gmbh/id1484041676?uo=2"
        }
      },
      "title": {
        "label": "AON CySec - cyan Security Group GmbH"
      },
      "link": {
        "attributes": {
          "rel": "alternate",
          "type": "text/html",
          "href": "https://apps.apple.com/us/app/aon-cysec/id1498892428?uo=2"
        }
      },
      "id": {
        "label": "https://apps.apple.com/us/app/aon-cysec/id1498892428?uo=2",
        "attributes": {
          "im:id": "1498892428",
          "im:bundleId": "com.aon.ios.eps"
        }
      },
      "im:contentType": {
        "attributes": {
          "term": "Application",
          "label": "Application"
        }
      },
      "category": {
        "attributes": {
          "im:id": "6002",
          "term": "Utilities",
          "scheme": "https://apps.apple.com/us/genre/ios-utilities/id6002?uo=2",
          "label": "Utilities"
        }
      },
      "im:releaseDate": {
        "label": "2020-04-30T00:00:00-07:00",
        "attributes": {
          "label": "April 30, 2020"
        }
      }
    }"""

    fun getMockFeedsData(): FeedsResponse = Gson().fromJson(response, FeedsResponse::class.java)

    fun getMockEntity() : ImEntity = Gson().fromJson(entity,ImEntity::class.java)

}