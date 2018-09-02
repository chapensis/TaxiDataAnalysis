<template>
  <div id="allmap" style="width: 100%;height: 550px;" />
</template>

<script>
import BMap from 'BMap'

export default {
  name: 'RoadMapDetail',
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
      var map = new BMap.Map('allmap');
      var point = new BMap.Point(this.$route.params[0].roadLon, this.$route.params[0].roadLat);
      map.centerAndZoom(point, 14);
      for (const position of this.$route.params) {
        var p = new BMap.Point(position.roadLon, position.roadLat);
        var marker = new BMap.Marker(p);
        map.addOverlay(marker);
      }
      map.enableScrollWheelZoom(true);
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
