<template>
	<view>
		<!-- 地址 -->
		<navigator url="/pages/address/address?source=1" class="address-section">
			<view class="order-content">
				<text class="yticon icon-shouhuodizhi"></text>
				<view class="cen">
					<view class="top">
						<text class="name">{{userInfo.username}}</text>
						<text class="mobile">{{userInfo.vxnumber}}</text>
					</view>
					<text class="address">{{userInfo.address}} 学院：{{userInfo.faculty}}</text>
				</view>
				<text class="yticon icon-you"></text>
			</view>
			<image class="a-bg" src="../../static/line.png"></image>
		</navigator>

		<view class="goods-section">
			<view class="g-header b-b">
				<image class="logo" src="../../static/missing-face.png"></image>
				<text class="name">{{goodinfo.owner_name}}</text>
			</view>
			<!-- 商品列表 -->
			<view class="g-item">
				<image :src="goodinfo.img_url"></image>
				<view class="right">
					<text class="title clamp">{{goodinfo.productname}}</text>
					<!-- <text class="spec">春装款 L</text> -->
					<view class="price-box">
						<text class="price">￥{{goodinfo.price}}</text>
						<!-- <text class="number">x 1</text> -->
					</view>
				</view>
			</view>
		</view>

		<!-- 金额明细 -->
		<view class="yt-list">
			<view class="yt-list-cell b-b">
				<text class="cell-tit clamp">商品金额</text>
				<text class="cell-tip">￥{{goodinfo.price}}</text>
			</view>
			<view class="yt-list-cell b-b">
				<text class="cell-tit clamp">交易方式</text>
				<text class="cell-tip">面交</text>
			</view>
			<view class="yt-list-cell desc-cell">
				<text class="cell-tit clamp">备注</text>
				<input class="desc" type="text" v-model="desc" placeholder="请填写备注信息" placeholder-class="placeholder" />
			</view>
		</view>

		<!-- 底部 -->
		<view class="footer">
			<view class="price-content">
				<text>实付款</text>
				<text class="price-tip">￥</text>
				<text class="price">{{goodinfo.price}}</text>
			</view>
			<text class="submit" @click="addOrder">提交订单</text>
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
				desc: '', //备注
				payType: 1, //1微信 2支付宝
				goodinfo: {
					id: -1,
					productname: "",
					price: "",
					type: "",
					detail: "",
					owner_id: "",
					owner_name: "",
					img_url: ""
				},
			}
		},
		computed: {
			...mapState(['hasLogin', 'userInfo'])
		},
		onLoad(option) {
			this.getProductInfo(option.id);
		},
		methods: {
			//显示优惠券面板
			toggleMask(type) {
				let timer = type === 'show' ? 10 : 300;
				let state = type === 'show' ? 1 : 0;
				this.maskState = 2;
				setTimeout(() => {
					this.maskState = state;
				}, timer)
			},
			numberChange(data) {
				this.number = data.number;
			},
			changePayType(type) {
				this.payType = type;
			},

			//获取商品信息
			getProductInfo(id) {
				var that = this;
				uni.request({
					url: 'http://localhost:8080/product/getproductbyid?',
					method: 'GET',
					data: {
						id: id,
					},
					success: function(res) {
						that.goodinfo = res.data
						console.log(res.data)
					},
					fail: function(res) {
						console.log(res)
					}

				});
			},
			addOrder() {
				//未登录需先登录
				if (!this.hasLogin) {
					uni.navigateTo({
						url: '../public/login'
					})
				} else {

					var now = new Date();
					this.AddOrder(now);
				}
			},
			AddOrder(now) {
				var that = this;
				console.log(that.userInfo);
				var nowdatetime = this.formateTime(now);

				uni.request({
					url: 'http://localhost:8080/order/addorder',
					method: 'POST',
					data: {
						datetime: nowdatetime,
						buyerid: new Number(that.userInfo.id),
						buyername: that.userInfo.username,
						sellerid: new Number(that.goodinfo.owner_id),
						sellername: that.goodinfo.owner_name,
						productid: new Number(that.goodinfo.id),
						productname: that.goodinfo.productname,
						price: that.goodinfo.price,
						img_url: that.goodinfo.img_url
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: function(res) {
						console.log(res);
						uni.showLoading({
							title: '请稍后'
						})
						setTimeout(function() {
							uni.redirectTo({
								url: '/pages/money/paySuccess'
							})
						}, 2000);

					},
					fail: function(res) {
						console.log(res)
					},
				});
			},
			formateTime(now) {

				var str = "YYYY-MM-DD hh:mm:ss";
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

				str = str.replace(/ss|SS/, now.getSeconds() > 9 ? now.getSeconds().toString() : '0' + now.getSeconds());
				str = str.replace(/s|S/g, now.getSeconds());

				return str;

			},
			stopPrevent() {}
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
		padding-bottom: 100upx;
	}

	.address-section {
		padding: 30upx 0;
		background: #fff;
		position: relative;

		.order-content {
			display: flex;
			align-items: center;
		}

		.icon-shouhuodizhi {
			flex-shrink: 0;
			display: flex;
			align-items: center;
			justify-content: center;
			width: 90upx;
			color: #888;
			font-size: 44upx;
		}

		.cen {
			display: flex;
			flex-direction: column;
			flex: 1;
			font-size: 28upx;
			color: $font-color-dark;
		}

		.name {
			font-size: 34upx;
			margin-right: 24upx;
		}

		.address {
			margin-top: 16upx;
			margin-right: 20upx;
			color: $font-color-light;
		}

		.icon-you {
			font-size: 32upx;
			color: $font-color-light;
			margin-right: 30upx;
		}

		.a-bg {
			position: absolute;
			left: 0;
			bottom: 0;
			display: block;
			width: 100%;
			height: 5upx;
		}
	}

	.goods-section {
		margin-top: 16upx;
		background: #fff;
		padding-bottom: 1px;

		.g-header {
			display: flex;
			align-items: center;
			height: 84upx;
			padding: 0 30upx;
			position: relative;
		}

		.logo {
			display: block;
			width: 50upx;
			height: 50upx;
			border-radius: 100px;
		}

		.name {
			font-size: 30upx;
			color: $font-color-base;
			margin-left: 24upx;
		}

		.g-item {
			display: flex;
			margin: 20upx 30upx;

			image {
				flex-shrink: 0;
				display: block;
				width: 140upx;
				height: 140upx;
				border-radius: 4upx;
			}

			.right {
				flex: 1;
				padding-left: 24upx;
				overflow: hidden;
			}

			.title {
				font-size: 30upx;
				color: $font-color-dark;
			}

			.spec {
				font-size: 26upx;
				color: $font-color-light;
			}

			.price-box {
				display: flex;
				align-items: center;
				font-size: 32upx;
				color: $font-color-dark;
				padding-top: 10upx;

				.price {
					margin-bottom: 4upx;
				}

				.number {
					font-size: 26upx;
					color: $font-color-base;
					margin-left: 20upx;
				}
			}

			.step-box {
				position: relative;
			}
		}
	}

	.yt-list {
		margin-top: 16upx;
		background: #fff;
	}

	.yt-list-cell {
		display: flex;
		align-items: center;
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

			&.disabled {
				color: $font-color-light;
			}

			&.active {
				color: $base-color;
			}

			&.red {
				color: $base-color;
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

	/* 支付列表 */
	.pay-list {
		padding-left: 40upx;
		margin-top: 16upx;
		background: #fff;

		.pay-item {
			display: flex;
			align-items: center;
			padding-right: 20upx;
			line-height: 1;
			height: 110upx;
			position: relative;
		}

		.icon-weixinzhifu {
			width: 80upx;
			font-size: 40upx;
			color: #6BCC03;
		}

		.icon-alipay {
			width: 80upx;
			font-size: 40upx;
			color: #06B4FD;
		}

		.icon-xuanzhong2 {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 60upx;
			height: 60upx;
			font-size: 40upx;
			color: $base-color;
		}

		.tit {
			font-size: 32upx;
			color: $font-color-dark;
			flex: 1;
		}
	}

	.footer {
		position: fixed;
		left: 0;
		bottom: 0;
		z-index: 995;
		display: flex;
		align-items: center;
		width: 100%;
		height: 90upx;
		justify-content: space-between;
		font-size: 30upx;
		background-color: #fff;
		z-index: 998;
		color: $font-color-base;
		box-shadow: 0 -1px 5px rgba(0, 0, 0, .1);

		.price-content {
			padding-left: 30upx;
		}

		.price-tip {
			color: $base-color;
			margin-left: 8upx;
		}

		.price {
			font-size: 36upx;
			color: $base-color;
		}

		.submit {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 280upx;
			height: 100%;
			color: #fff;
			font-size: 32upx;
			background-color: $base-color;
		}
	}

	/* 优惠券面板 */
	.mask {
		display: flex;
		align-items: flex-end;
		position: fixed;
		left: 0;
		top: var(--window-top);
		bottom: 0;
		width: 100%;
		background: rgba(0, 0, 0, 0);
		z-index: 9995;
		transition: .3s;

		.mask-content {
			width: 100%;
			min-height: 30vh;
			max-height: 70vh;
			background: #f3f3f3;
			transform: translateY(100%);
			transition: .3s;
			overflow-y: scroll;
		}

		&.none {
			display: none;
		}

		&.show {
			background: rgba(0, 0, 0, .4);

			.mask-content {
				transform: translateY(0);
			}
		}
	}

	/* 优惠券列表 */
	.coupon-item {
		display: flex;
		flex-direction: column;
		margin: 20upx 24upx;
		background: #fff;

		.con {
			display: flex;
			align-items: center;
			position: relative;
			height: 120upx;
			padding: 0 30upx;

			&:after {
				position: absolute;
				left: 0;
				bottom: 0;
				content: '';
				width: 100%;
				height: 0;
				border-bottom: 1px dashed #f3f3f3;
				transform: scaleY(50%);
			}
		}

		.left {
			display: flex;
			flex-direction: column;
			justify-content: center;
			flex: 1;
			overflow: hidden;
			height: 100upx;
		}

		.title {
			font-size: 32upx;
			color: $font-color-dark;
			margin-bottom: 10upx;
		}

		.time {
			font-size: 24upx;
			color: $font-color-light;
		}

		.right {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			font-size: 26upx;
			color: $font-color-base;
			height: 100upx;
		}

		.price {
			font-size: 44upx;
			color: $base-color;

			&:before {
				content: '￥';
				font-size: 34upx;
			}
		}

		.tips {
			font-size: 24upx;
			color: $font-color-light;
			line-height: 60upx;
			padding-left: 30upx;
		}

		.circle {
			position: absolute;
			left: -6upx;
			bottom: -10upx;
			z-index: 10;
			width: 20upx;
			height: 20upx;
			background: #f3f3f3;
			border-radius: 100px;

			&.r {
				left: auto;
				right: -6upx;
			}
		}
	}
</style>
