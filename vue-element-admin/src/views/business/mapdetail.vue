<template>
  <div id="allmap" style="width: 100%;height: 550px;" />
</template>

<script>
import BMap from 'BMap'

export default {
  name: 'BusinessMapDetail',
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
      // 百度地图API功能
      var map = new BMap.Map('allmap');
      map.centerAndZoom(new BMap.Point(this.$route.params.onLon, this.$route.params.onLat), 12);
      // var point = new BMap.Point(this.$route.params[0].roadLon, this.$route.params[0].roadLat);
      var p1 = new BMap.Point(this.$route.params.onLon, this.$route.params.onLat);
      var p2 = new BMap.Point(this.$route.params.offLon, this.$route.params.offLat);

      var driving = new BMap.DrivingRoute(map, { renderOptions: { map: map, autoViewport: true }});
      driving.search(p1, p2);
      map.enableScrollWheelZoom(true);
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
