
### 创建索引
````
PUT comet
{
  "settings": {
    "number_of_replicas": 0,
    "number_of_shards": 5,
    "index.store.type": "niofs",
    "index.query.default_field": "title",
    "index.unassigned.node_left.delayed_timeout": "5m"
  },

  "mappings": {
    "comet": {
      "dynamic": "strict",
      "properties": {
        "cometId": {
          "type": "long"
        },
        "title": {
          "type": "text",
          "analyzer": "ik_smart",
          "search_analyzer": "ik_smart"
        },
        "author": {
           "type": "text",
           "analyzer": "ik_smart",
           "search_analyzer": "ik_smart"
         },
        "editor": {
          "type": "keyword"
        },
        "category": {
          "type": "keyword"
        },
        "description": {
          "type": "text",
          "analyzer": "ik_smart",
          "search_analyzer": "ik_smart"
        },
        "content": {
          "type": "text",
          "analyzer": "ik_smart",
          "search_analyzer": "ik_smart"
        },
        "createTime": {
          "type": "date",
          "format": "strict_date_optional_time||epoch_millis"
        },
        "suggest": {
          "type": "completion"
        }
      }
    }
  }
}
````