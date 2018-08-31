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
      console.log('this.$route.params', this.$route.params);
      var point = new BMap.Point(this.$route.params.roadLon, this.$route.params.roadLat);
      map.centerAndZoom(point, 14);
      map.enableScrollWheelZoom(true);
      var marker = new BMap.Marker(point);
      map.addOverlay(marker);
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
