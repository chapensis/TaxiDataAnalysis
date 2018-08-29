<template>
  <div id="road-list">
    <div class = "road-list">
      <!--查询条件-->
      <template>
        路段名：
        <el-input v-model="queryRoadName"
                  placeholder="请输入查询路段名"
                  prefix-icon="el-icon-search"
                  class="input-with-select"
                  size="mini" />
        <el-button type="primary"
                   size="mini"
                   @click="filter">搜索</el-button>
      </template>
      <div style="margin-top:15px;" />
      <template>
        <el-table
          v-loading="isLoading"
          :data="roadInfo"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'roadId', order: 'descending'}"
          stripe
          border
          highlight-current-row
          fit
          style="width: 100%">
          <el-table-column label="路段Id" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.roadId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段名" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.roadName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段经度">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLon }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段纬度">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段长度">
            <template slot-scope="scope">
              <span>{{ scope.row.roadLength }}</span>
            </template>
          </el-table-column>
          <el-table-column label="路段时间">
            <template slot-scope="scope">
              <span>{{ scope.row.roadTime }}</span>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'RoadList',
  components: {
    CountTo
  },
  data() {
    return {
      queryRoadName: '',
      pageNum: 1,
      pageSize: 10,
      isLoading: '',
      loadingText: ''
    }
  },
  computed: {
    ...mapState('road', ['roadInfo'])
  },
  created() {
    this.loadingText = '正在加载路段数据'
    this.isLoading = true
    this.getRoadInfo({
      pageNum: this.pageNum,
      pageSize: this.pageSize
    })
      .then(() => {
        this.isLoading = false
      })
      .catch(err => {
        this.isLoading = false
        if (typeof err === 'string' && err !== 'cancel') {
          this.$message.error(err)
        }
      })
  },
  methods: {
    ...mapActions('road', ['getRoadInfo']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },

    filter() {
      this.$message.info('当前功能正在开发中......');
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
#road-list {
  .road-list {
    margin: 50px;
    align: "center"
  }
  .input-with-select {
    width: 150px;
    margin-right:15px
  }
}
</style>
