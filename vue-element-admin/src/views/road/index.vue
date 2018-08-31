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
          :data="roadList"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'roadId', order: 'descending'}"
          stripe
          border
          highlight-current-row
          fit
          row-class-name="align-center"
          header-cell-class-name="align-center"
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
          <el-table-column label="路段时间(分钟)">
            <template slot-scope="scope">
              <span>{{ scope.row.roadTime }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed = "right" label = "操作" width = "120">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="showRoadDetail(scope.row)">
                查看详细
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <el-pagination
        :total="totalRoadNum"
        :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        background
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
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
    ...mapState('road', ['roadList', 'totalRoadNum'])
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
      });

    this.getTotalRoadNum()
      .then(() => {
        // this.isLoading = false
      })
      .catch(err => {
        // this.isLoading = false
        if (typeof err === 'string' && err !== 'cancel') {
          this.$message.error(err)
        }
      })
  },
  methods: {
    ...mapActions('road', ['getRoadInfo', 'getTotalRoadNum']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },

    handleSizeChange(val) {
      this.loadingText = '正在加载路段数据'
      this.isLoading = true
      this.getRoadInfo({
        pageNum: this.pageNum,
        pageSize: val
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

    handleCurrentChange(val) {
      this.loadingText = '正在加载路段数据'
      this.isLoading = true
      this.getRoadInfo({
        pageNum: val,
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

    showRoadDetail(row) {
      this.$router.push({
        name: 'RoadMapdetail',
        params: {
          roadLon: row.roadLon,
          roadLat: row.roadLat
        }
      });
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
    align: "center";
  }
  .input-with-select {
    width: 150px;
    margin-right:15px
  }
  .align-center {
    text-align: center;
  }
}
</style>
