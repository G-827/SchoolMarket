<template>
	<view class="container">
		<view class="c-list">
			<view class="imagePart">
				<template v-if="hasAddImage==false">
					<image class="image-def" src="../../static/photo.png" mode="aspectFit" @click="ChooseImage"></image>
					<text class="tit mid">添加照片</text>
				</template>

				<template v-if="hasAddImage==true">
					<image :src="addData.img_url" mode="widthFix"></image>
				</template>
			</view>

			<view class="productPart">
				<view class="c-row">
					<text class="tit">宝贝名称</text>
					<view class="bz-list con">
						<input placeholder="添加名称" v-model="addData.productname"></input>
					</view>
				</view>
				<view class="c-row">
					<text class="tit">宝贝描述</text>
					<view class="bz-list con">
						<textarea placeholder="在这里详细描述一下宝贝的转手原因、入手渠道、规格尺寸、新旧程度和使用感受吧~"
							v-model="addData.detail"></textarea>
					</view>
				</view>
			</view>
			<view class="yt-list">
				<view class="yt-list-cell b-b">
					        <view class="cell-tit clamp">
					            分类
					        </view>
							<view class="uni-list-cell-db">
								<picker @change="bindPickerChange" :value="index" :range="array">
									<view class="uni-input">{{array[index]}}</view>
								</picker>
							</view>

				</view>
				<view class="yt-list-cell b-b">
					<text class="cell-tit clamp">价格</text>
					<view class="cell-tip red">
						<input placeholder="设置价格" v-model="addData.price"></input>
						<!-- <text>￥</text> -->
					</view>
				</view>
			</view>
		</view>

		<view class="subPart">
			<button class="subBtn" form-type="submit" hover-class="subBtnPrs" @click="submitProduct">发布</button>
		</view>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	export default {
		data() {
			return {
				title: 'picker',
				array: ['书籍教材', '交通工具', '服装鞋帽', '生活百货', '手机数码', '其他'],
				index: 0,
				hasAddImage: false,
				addData: {
					productname: "",
					price: "",
					type: "",
					detail: "",
					owner_id: -1,
					owner_name: "",
					img_url: ""
				}
			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo'])
		},
		methods: {
			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为', e.target.value)
				this.index = e.target.value
			},
			submitProduct() {
				//必须登录
				//console.log(this.isLogin);
				if (!this.hasLogin) {
					uni.navigateTo({
						url: '../public/login'
					});
				} else {
					this.addProduct()
				}

			},
			addProduct() {
				if (!this.hasLogin) {
					uni.navigateTo({
						url: '../public/login'
					})
				} else if (this.addData.productname == "" || this.addData.price == "" || this.addData.detail == "") {
					if (this.addData.productname == "") {
						this.$api.msg("宝贝名不得为空哦");
					} else if (this.addData.price == "") {
						this.$api.msg("请输入意向价格");
					} else if (this.addData.detail == "") {
						this.$api.msg("描述一下你的闲置吧");
					}
				} else {
					var now = new Date();
					this.AddProduct(now);
				}
			},
			AddProduct(now) {
				var that = this;
				//console.log(that.userInfo);
				var nowdatetime = that.formateTime(now);
				uni.request({
					url: 'http://localhost:8080/product/addproduct',
					method: 'POST',
					data: {
						id: -1,
						productname: that.addData.productname,
						price: that.addData.price,
						type: that.addData.type,
						detail: that.addData.detail,
						img_url: that.addData.img_url,
						owner_id: that.userInfo.id,
						owner_name: that.userInfo.username,
						datetime: nowdatetime
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: function(res) {
						console.log(res);
						uni.showToast({
							title: '发布成功！',
							icon: 'success'
						})
						uni.switchTab({
							url: '../index/index'
						})
					},
					fail: function(res) {
						console.log(res)
					},
				});

			},
			formateTime(now) {

				var str = "YYYY-MM-DD hh:mm";
				var Week = ['日', '一', '二', '三', '四', '五', '六'];

				str = str.replace(/yyyy|YYYY/, now.getFullYear());
				//str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():’0′ + (this.getYear() % 100));

				var month = now.getMonth() + 1;
				str = str.replace(/MM/, month > 9 ? month.toString() : '0' + month);
				//str=str.replace(/M/g,this.getMonth());

				str = str.replace(/w|W/g, Week[now.getDay()]);

				str = str.replace(/dd|DD/, now.getDate() > 9 ? now.getDate().toString() : '0' + now.getDate());
				str = str.replace(/d|D/g, now.getDate());

				str = str.replace(/hh|HH/, now.getHours() > 9 ? now.getHours().toString() : '0' + now.getHours());
				str = str.replace(/h|H/g, now.getHours());
				str = str.replace(/mm/, now.getMinutes() > 9 ? now.getMinutes().toString() : '0' + now.getMinutes());
				str = str.replace(/m/g, now.getMinutes());

				return str;

			},
			addPhoto() {

			},
			ChooseImage() {
				console.log("addphoto");
				let self = this;
				uni.chooseImage({
					count: 1, //可选择数量，默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], //从相册选择或从使用相机
					success: async (res) => {
						//上传图片大小限制
						//console.log(res);
						//console.log(res.tempFilePaths)
						if (res.tempFiles[0].size > 10 * 1024 * 1000) { //上传图片大小限制
							uni.showToast({
								title: "照片大小不能超过10MB",
								icon: "none"
							});
							return
						}

						try {
							uni.showLoading({
								title: "上传中...",
								mask: true
							});
							uni.uploadFile({
								url: 'http://localhost:8080/product/addimage', //post请求的地址
								filePath: res.tempFilePaths[0],
								name: 'file',
								formData: {
									'productid': self.goodid //formData是指除了图片以外，额外加的字段
								},
								success: (uploadFileRes) => {
									//这里要注意，uploadFileRes.data是个String类型，要转对象的话需要JSON.parse一下
									let obj = JSON.parse(uploadFileRes.data);
									console.log(obj.img_url);
									//console.log(obj.data);//后端返回数据
									//self.attachmentId = obj.data.relationId;
									// let image = {
									// 	//'id': obj.data.id,
									// 	//'relationId': obj.data.relationId,
									// 	'url': res.tempFilePaths[0],
									// };
									self.addData.img_url = obj.img_url;
									self.hasAddImage = true;
								},
								fail: (res) => {
									console.log("uploadFile fail");
									console.log(res);
								}
							})
						} catch (e) {
							console.log("chooseimge catch error")
							console.log(e);
						} finally {
							uni.hideLoading();
						}
					}
				});
			},
		}
	}
</script>


<style lang="scss">
	page {
		background: $page-color-base;
	}
	
	.c-list {
		font-size: $font-sm + 2upx;
		color: $font-color-base;
		background: $page-color-base;

		.imagePart {
			background: #ffffff;
			height: 500upx;
			display: flex;
			flex-direction: column;
			align-items: center;
			align-content: center;
			justify-content: center;

			.image-def {
				width: 86upx;
				height: 86upx;
				//display: block;		
			}

		}


		.c-row {
			display: flex;
			align-items: center;
			margin: 10upx;
			padding: 20upx 30upx;
			position: relative;
		}

		.tit {
			width: 140upx;

		}

		.mid {
			text-align: center;
		}

		.con {
			flex: 1;
			color: #606266;

			.selected-text {
				margin-right: 10upx;
			}
		}

		.bz-list {
			height: 40upx;
			font-size: $font-sm+2upx;
			color: $font-color-dark;

			text {
				display: inline-block;
				margin-right: 30upx;
			}

			textarea {
				height: 300upx;
				width: 530upx;
			}
		}

		.con-list {
			flex: 1;
			display: flex;
			flex-direction: column;
			color: $font-color-dark;
			line-height: 40upx;
		}

		.red {
			color: $uni-color-primary;
		}
	}


	.productPart {
		position: relative;
		margin-top: 20upx;
		background: #fff;
		padding-bottom: 1px;
		height: 400upx;

	}

	.yt-list {
		margin-top: 16upx;
		background: $page-color-base;
		padding-bottom: 300upx;
	}

	.yt-list-cell {
		display: flex;
		background: #ffffff;
		align-items: center;
		margin: 10upx 0upx;
		padding: 10upx 30upx 10upx 40upx;
		line-height: 70upx;
		position: relative;

		&.cell-hover {
			background: #fafafa;
		}

		&.b-b:after {
			left: 30upx;
		}

		.cell-icon {
			height: 32upx;
			width: 32upx;
			font-size: 22upx;
			color: #fff;
			text-align: center;
			line-height: 32upx;
			background: #f85e52;
			border-radius: 4upx;
			margin-right: 12upx;

			&.hb {
				background: #ffaa0e;
			}

			&.lpk {
				background: #3ab54a;
			}

			.red {
				color: $uni-color-primary;
			}

		}

		.cell-more {
			align-self: center;
			font-size: 24upx;
			color: $font-color-light;
			margin-left: 8upx;
			margin-right: -10upx;
		}

		.cell-tit {
			flex: 1;
			font-size: 26upx;
			color: $font-color-light;
			margin-right: 10upx;
		}

		.cell-tip {
			font-size: 26upx;
			color: $font-color-dark;
			right: 0;

			&.disabled {
				color: $font-color-light;
			}

			&.active {
				color: $base-color;
			}

			&.red {
				color: $base-color;
			}

			input {
				text-align: right;
			}
		}

		&.desc-cell {
			.cell-tit {
				max-width: 90upx;
			}
		}

		.desc {
			flex: 1;
			font-size: $font-base;
			color: $font-color-dark;
		}
	}
	
	
	
	.uni-list-cell-left{
		display: block;
		float: left;
		color: #606266;
	}
	
	.uni-list-cell-db{
		display: block;
		float: right;
		color: #606266;
	}
	
	.clamp{
		color: black;
	}


	.subPart {
		position: fixed;
		bottom: 110upx;
		left: 0;
		right: 0;
		padding: 30upx 40upx;
		//background-color: #;

		.subBtn {
			background: linear-gradient(to right, #ffac30, #fa436a, #ffac30);
			border-radius: 100px;
			font-size: 25upx;
			font-weight: bold;
			color: #ffffff;
			box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
		}

		.subBtnPrs {
			background: #ea3e63;
			top: 3upx;
			left: 3upx;
			box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
		}
	}
</style>
