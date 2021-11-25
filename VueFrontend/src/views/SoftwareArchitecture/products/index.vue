<template>
  <div>
    <el-dialog
      title="警告"
      :visible.sync="InvalidInputDialogVisible"
      width="30%"
      :show-close="false"
    >
      <span>输入无效</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="InvalidInputDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改"
      :visible.sync="UpdatedialogVisible"
      width="30%"
    >
      <el-form :model="updateData" :inline="true">
        <el-form-item label="ID">
          <el-input v-model="updateData.id" :disabled="true" size="small" />
        </el-form-item>
        <el-form-item label="商品名">
          <el-input v-model="updateData.productname" size="small" placeholder="商品名" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="updateData.price" size="small" placeholder="价格" />
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="updateData.type" size="small" placeholder="类型" />
          <!-- <el-select v-model="updateData.type" size="small" placeholder="类型">
            <el-option v-for="(item, index) in types" :key="index" :label="item" :value="item" /> -->
          <!-- </el-select> -->
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="updateData.detail" size="small" placeholder="描述" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="commitEdit">确 定</el-button>
      </span>
    </el-dialog>
    <el-divider />
    <el-row>
      <el-col :span="1"><br></el-col>
      <el-col :span="23">
        <el-form :inline="true" :model="addData">
          <el-form-item label="商品名">
            <el-input v-model="addData.productname" size="small" placeholder="商品名" />
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="addData.price" size="small" placeholder="价格" />
          </el-form-item>
          <el-form-item label="类型">
            <el-input v-model="addData.type" size="small" placeholder="类型" />
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="addData.detail" size="small" placeholder="描述" />
          </el-form-item>
          <el-form-item>
            <el-button type="success" size="mini" @click="AddProduct">添加</el-button>
            <el-button type="primary" size="mini" @click="Search">搜索</el-button>
            <el-button type="plain" size="mini" @click="getAllProduct">刷新</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-table
      id="TableTop"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%"
      @sort-change="SortById"
    >
      <el-table-column
        align="center"
        prop="id"
        label="ID"
        sortable="custom"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="商品名"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.productname }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="价格"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.price }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="类型"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="描述"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.detail }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="图片地址"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.img_url }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="handleEdit(scope.row.id, scope.row)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="text-align: center;margin-top: 30px;">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :pager-count="5"
        layout="total, pager"
        :total="tableData.length"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Index',
  data() {
    return {
      currentPage: 1,
      pageSize: 6,
      tableData: [],
      allData: [],
      InvalidInputDialogVisible: false,
      UpdatedialogVisible: false,
      // types: ['', '信息学部', '文理学部', '工学部', '医学部'],
      addData: {
        'id': -1,
        'productname': '',
        'price': '',
        'type': '',
        'detail': ''
      },
      updateData: {
        'id': -1,
        'productname': '',
        'price': '',
        'type': '',
        'detail': ''
      },
      sortState: 0
    }
  },
  created() {
    var that = this
    that.getAllProduct()
  },
  methods: {
    getAllProduct() {
      var that = this
      axios.get(this.$store.state.productHeadUrl + 'getallproduct').then(function(response) {
        that.allData = response.data
        that.tableData = response.data
        console.log(that.products)
      }).catch(function(error) {
        console.log(error)
      })
    },
    deleteProductById(id) {
      axios.delete(this.$store.state.productHeadUrl + 'deleteproductbyid?id=' + id).then(function(response) {
        console.log(response.data)
      }).catch(function(error) {
        console.log(error)
      })
    },
    addProduct() {
      var that = this
      axios({
        method: 'post',
        url: this.$store.state.productHeadUrl + 'addproduct',
        data: {
          'id': that.addData.id,
          'productname': that.addData.productname,
          'price': that.addData.price,
          'type': that.addData.type,
          'detail': that.addData.detail
        },
        transformRequest: [
          function(data) {
            let ret = ''
            for (const it in data) {
              ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            ret = ret.substring(0, ret.lastIndexOf('&'))
            return ret
          }
        ],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(function(response) {
        that.getAllProduct()
      })
    },
    updateProduct() {
      var that = this
      axios({
        method: 'put',
        url: this.$store.state.productHeadUrl + 'updateproduct',
        data: {
          'id': that.updateData.id,
          'productname': that.updateData.productname,
          'price': that.updateData.price,
          'type': that.updateData.type,
          'detail': that.updateData.detail
        },
        transformRequest: [
          function(data) {
            let ret = ''
            for (const it in data) {
              ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            ret = ret.substring(0, ret.lastIndexOf('&'))
            return ret
          }
        ],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(function(response) {
        that.getAllProduct()
      })
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      location.href = '#TableTop'
    },
    IndexOfId(id) {
      var index = -1
      for (var i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].id === id) {
          index = i
        }
      }
      return index
    },
    handleDelete(id, row) {
      var index = this.IndexOfId(id)
      console.log('index:' + index)
      this.tableData.splice(index, 1)
      if (this.tableData.length % this.pageSize === 0) {
        this.currentPage--
      }
      console.log('id:' + id)
      this.deleteProductById(id)
    },
    handleEdit(id, row) {
      this.UpdatedialogVisible = true
      var index = this.IndexOfId(id)
      this.updateData = JSON.parse(JSON.stringify(this.tableData[index]))
    },
    commitEdit() {
      this.updateProduct()
      this.UpdatedialogVisible = false
    },
    AddProduct() {
      var that = this
      if (that.addData.productname === '' || that.addData.type === '') {
        that.InvalidInputDialogVisible = true
        return
      }
      console.log(that.addData)
      that.addProduct()
    },
    Search() {
      var that = this
      that.currentPage = 1
      that.tableData = that.allData.filter(item => {
        return item.productname.includes(that.addData.productname) && item.type.includes((that.addData.type))
      })
      console.log(that.tableData)
    },
    SortById() {
      this.sortState = (this.sortState + 1) % 3
      if (this.sortState === 1) {
        this.tableData.sort((a, b) => b.id - a.id)
      } else if (this.sortState === 2) {
        this.tableData.reverse()
      }
      //console.log(this.sortState)
    }
  }
}
</script>

<style scoped>
</style>
