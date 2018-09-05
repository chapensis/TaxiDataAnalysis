<template>
  <div id="business-list">
    <div class = "business-list">
      <!--查询条件-->
      <template>
        交易名：
        <el-input v-model="queryBusinessName"
                  placeholder="请输入查询交易名"
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
          :data="businessList"
          :element-loading-text="loadingText"
          :default-sort="{prop: 'businessId', order: 'descending'}"
          stripe
          border
          highlight-current-row
          fit
          row-class-name="align-center"
          header-cell-class-name="align-center"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="上车时间">
                  <span>{{ props.row.onTime }}</span>
                </el-form-item>
                <el-form-item label="下车时间">
                  <span>{{ props.row.offTime }}</span>
                </el-form-item>
                <el-form-item label="车牌Id">
                  <span>{{ props.row.unitId }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="交易Id" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.businessId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="交易时间" width="180">
            <template slot-scope="scope">
              <span>{{ scope.row.stamp | formatSecond('YYYY-MM-DD HH:mm:ss') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="上车经度" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.onLon }}</span>
            </template>
          </el-table-column>
          <el-table-column label="上车纬度" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.onLat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="下车经度" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.offLon }}</span>
            </template>
          </el-table-column>
          <el-table-column label="下车纬度" width="100">
            <template slot-scope="scope">
              <span>{{ scope.row.offLat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="运行长度">
            <template slot-scope="scope">
              <span>{{ scope.row.runLength }}</span>
            </template>
          </el-table-column>
          <el-table-column label="交易金额">
            <template slot-scope="scope">
              <span>{{ scope.row.runMoney }}</span>
            </template>
          </el-table-column>
          <el-table-column label="运行时间">
            <template slot-scope="scope">
              <span>{{ scope.row.runTime }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed = "right" label = "操作" width = "100">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="showBusinessDetail(scope.row)">
                定位
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <el-pagination
        :total="businessListNum"
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
  name: 'BusinessList',
  components: {
    CountTo
  },
  data() {
    return {
      queryBusinessName: '',
      pageNum: 1,
      pageSize: 10,
      isLoading: '',
      loadingText: ''
    }
  },
  computed: {
    ...mapState('business', ['businessList', 'businessListNum'])
  },
  created() {
    this.loadingText = '正在加载交易数据';
    this.isLoading = true;
    Promise.all([
      this.getBusinessList({
        pagination: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }),
      this.getTotalBusinessNum({
        businessName: this.queryBusinessName
      })
    ])
      .then(() => {
        this.isLoading = false
      })
      .catch(err => {
        this.isLoading = false
        if (typeof err === 'string' && err !== 'cancel') {
          this.$message.error(err)
        }
      });
  },
  methods: {
    ...mapActions('business', ['getBusinessList', 'getTotalBusinessNum']),
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    handleSizeChange(val) {
      this.loadingText = '正在加载交易数据'
      this.isLoading = true
      this.pageSize = val;
      Promise.all([
        this.getBusinessList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          businessName: this.queryBusinessName
        }),
        this.getTotalBusinessNum({
          businessName: this.queryBusinessName
        })
      ])
        .then(() => {
          this.isLoading = false
        })
        .catch(err => {
          this.isLoading = false
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err)
          }
        });
    },

    handleCurrentChange(val) {
      this.loadingText = '正在加载交易数据';
      this.isLoading = true;
      this.pageNum = val;
      Promise.all([
        this.getBusinessList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          businessName: this.queryBusinessName
        }),
        this.getTotalBusinessNum({
          businessName: this.queryBusinessName
        })
      ])
        .then(() => {
          this.isLoading = false
        })
        .catch(err => {
          this.isLoading = false
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err)
          }
        });
    },

    showBusinessDetail(row) {
      this.$router.push({
        name: 'BusinessMapDetail',
        params: {
          onLon: row.onLon,
          onLat: row.onLat,
          offLon: row.offLon,
          offLat: row.offLat
        }
      });
    },

    filter() {
      this.loadingText = '正在加载交易数据'
      this.isLoading = true
      Promise.all([
        this.getBusinessList({
          pagination: {
            pageNum: this.pageNum,
            pageSize: this.pageSize
          },
          businessName: this.queryBusinessName
        }),
        this.getTotalBusinessNum({
          businessName: this.queryBusinessName
        })
      ])
        .then(() => {
          this.isLoading = false
        })
        .catch(err => {
          this.isLoading = false
          if (typeof err === 'string' && err !== 'cancel') {
            this.$message.error(err)
          }
        });
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" >
#business-list {
  .business-list {
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
  .demo-table-expand {
    font-size: 0;
  }
}
</style>
