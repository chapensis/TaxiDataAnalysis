<template>
  <div id="panorama" style="width: 100%;height: 550px;" />
</template>

<script>
import BMap from 'BMap'

export default {
  name: 'RoadMapPanorama',
  data() {
    return {

    }
  },
  computed: {

  },
  mounted() {
    this.baiduMap();
  },
  created() {

  },
  methods: {
    baiduMap() {
      var panorama = new BMap.Panorama('panorama');
      var point = new BMap.Point(this.$route.params.roadLon, this.$route.params.roadLat);
      panorama.setPosition(point);
      panorama.setPov({ pitch: 5.04, heading: 343.92 });

      // 设置相册位置为右上角
      panorama.setOptions({
        albumsControlOptions: {
          anchor: BMap.BMAP_ANCHOR_TOP_RIGHT
        }
      });

      // 设置偏移量，距离上面15px，距离左边100px（距离那边受anchor位置的影响）
      panorama.setOptions({
        albumsControlOptions: {
          offset: new BMap.Size(100, 15)
        }
      });

      // 设置相册的长度和图片大小,相册的最大宽度为60%，相册内图片的大小为80px
      panorama.setOptions({
        albumsControlOptions: {
          maxWidth: '60%',
          imageHeight: 80
        }
      });
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
