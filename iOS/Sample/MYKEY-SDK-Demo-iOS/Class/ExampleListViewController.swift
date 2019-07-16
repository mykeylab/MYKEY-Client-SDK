//
//  ExampleListViewController.swift
//  TestSDK
//
//  Created by mykeylab on 2019/6/19.
//  Copyright © 2019 MYKEY. All rights reserved.
//

import Foundation
import UIKit
import MYKEYWalletLib

let kscWidth = UIScreen.main.bounds.size.width
let kscHeight = UIScreen.main.bounds.size.height

class ExampleListViewController: UIViewController {
    
    let sectionTitles = ["DeepLink","","SimpleWallet","MYKEYWallet","EOS-Stake-Token"]
    
    let itemTitles = [
        ["MYKEYHome","OpenMYKEYDapp"],
        ["MYKEYWallet","MYKEYWalletSimple"],
        ["SW认证","SW-Contract","SW转账","SW签名"],
        ["MYKEY认证","MYKEY-Contract","MYKEY转账","MYKEY签名"],
        ["获取Token余额","获取解锁中的列表","获取用户绑定信息","锁仓","解锁","普通转账","转账并锁仓","解锁并转账"]
    ]
    
    lazy var sectionTitleLabel : UILabel = {
        let label = UILabel()
        label.textColor = UIColor.black
        label.font = UIFont.systemFont(ofSize: 16.0)
        return label
    }()
    
    var collectionView : UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: (kscWidth - 60)/2, height: 30)
        layout.minimumInteritemSpacing = 20
        layout.minimumLineSpacing = 20
        layout.sectionInset = UIEdgeInsets(top: 0, left: 20, bottom: 0, right: 20)
        
        collectionView = UICollectionView(frame: view.frame, collectionViewLayout: layout)
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.backgroundColor = UIColor.white
        view.addSubview(collectionView)
        
        collectionView.register(ExampleListCell.self, forCellWithReuseIdentifier: "123")
        collectionView.register(ExampleListSectionHeaderView.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: "234")
        
        collectionView.reloadData()
        
        
    }
}

extension ExampleListViewController : UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return sectionTitles.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return itemTitles[section].count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "123", for: indexPath) as! ExampleListCell
        cell.titleLabel.text = itemTitles[indexPath.section][indexPath.row]
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        return CGRect(x: 0, y: 0, width: kscWidth, height: 40).size
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if kind == UICollectionView.elementKindSectionHeader{
            let view = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: "234", for: indexPath) as! ExampleListSectionHeaderView
            if indexPath.section == 1{
                view.titleLabel.text = "是否初始化：\(!MYKEYWallet.shared.scheme.isEmpty)"
            }else{
                view.titleLabel.text = sectionTitles[indexPath.section]
            }
            return view
        }else{
            return UICollectionReusableView()
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if indexPath.section == 0{
            switch indexPath.row{
            case 0:
                print("MYKEY主页")
                openMYKEYHome()
                
            case 1:
                print("Dapp浏览器")
                openMYKEYDapp()
                
            default:
                break
            }
        }else if indexPath.section == 1{
            switch indexPath.row{
            case 0:
                print("初始化MYKEYWallet")
                initMYKEYWallet()
            case 1:
                print("初始化MYKEYWalletSimple")
                initMYKEYWalletSimple()
            default:
                break
            }

        }else if indexPath.section == 2{
            //SimpleWallet
            switch indexPath.row{
            case 0:
                print("sw认证")
                simpleWalletAuthori()
            case 1:
                print("swContract")
                simpleWalletContract()
            case 2:
                print("sw转账")
                simpleWalletTransfer()
            case 3:
                print("sw签名")
                simpleWalletSig()
            default:
                self.view.makeToast("暂未开放")
                break
            }
        }else if indexPath.section == 3{
            //MYKEYWallet
            switch indexPath.row{
            case 0:
                print("mykey认证")
                mykeyAuthorize()
            case 1:
                print("mykeyContract")
                mykeyContract()
            case 2:
                print("mykey转账")
                mykeyTransfer()
            case 3:
                print("mykey签名")
                mykeySign()
            default:
                self.view.makeToast("暂未开放")
                break
            }
        }else if indexPath.section == 4{
            //Contract
            print(itemTitles[indexPath.section][indexPath.row])
            switch indexPath.row{
            case 0:
                self.getTokenBalance()
            case 1:
                self.getUnlockList()
            case 2:
                self.getBoundInfo()
            case 3:
                self.stake()
            case 4:
                self.unStake()
            case 5:
                self.customTransfer()
            case 6:
                self.transferWithStake()
            case 7:
                self.unStakeWithTransfer()
            default:
                print("暂未开放")
                self.view.makeToast("暂未开放")

                break
            }
        }
    }
    

}

//MARK: -
extension ExampleListViewController{
    //MARK: 打开MYKEY首页
    func openMYKEYHome(){
        let mykeyHomeURL = URL(string: "mykey://mykey.org/")!
        UIApplication.shared.openURL(mykeyHomeURL)
    }
    
    //MARK: 打开MYKEY的KEY-EOS兑换(Dapp)页面
    func openMYKEYDapp(){
        
        let dappUrl = "https://m.bihu.com"
        let mykeyDappBaseUrl = String(format: "mykey://mykey.org/dapp?url=%@", dappUrl).addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? ""
        
        if let url = URL(string: mykeyDappBaseUrl){
            UIApplication.shared.openURL(url)
        }
    }
    
}
//MARK: -
extension ExampleListViewController{
    //MARK: 初始化 MYKEYWallet
    func initMYKEYWallet(){
        let initData = InitRequest()
        initData.appKey = "9mDxSbyR"
        initData.dappName = "SDK-Demo"
        initData.dappIcon = "https://n.sinaimg.cn/blog/transform/175/w105h70/20190702/0d66-hzfekep1090011.jpg"
        initData.UUID = UIDevice.current.identifierForVendor
        initData.scheme = "demoscheme"
        initData.disableInstall = false
        initData.mykeyServer = "https:dev-app.mykey.tech"
        MYKEYWallet.shared.initWallet(initData: initData)
        
        self.collectionView.reloadData()
        
        let initDict : Dictionary<String,Any> = [
            "appkey" : initData.appKey,
            "dappName" : initData.dappName,
            "dappIcon" : initData.dappIcon,
            "UUID" : initData.UUID?.uuidString ?? "",
            "scheme" : initData.scheme,
            "disableInstall" : initData.disableInstall
        ]
        
        presentDataView(data: initDict.toJsonString() ?? "")
    }
    
    //MARK: 初始化 MYKEYWalletSimple
    func initMYKEYWalletSimple(){
        let initSimpleData = InitSimpleRequest()
        initSimpleData.dappName = "Simple-SDK-Demo"
        initSimpleData.dappIcon = "https://oss02.bihu.com/img/b4e2dcd644c322b5bc9584082f0c5ff9.png?x-oss-process=style/size_head"
        initSimpleData.scheme = "demoscheme"
        initSimpleData.disableInstall = false
        MYKEYWallet.shared.initWalletSimple(initSimpleData: initSimpleData)
        
        self.collectionView.reloadData()

        let initDict : Dictionary<String,Any> = [
            "dappName" : initSimpleData.dappName,
            "dappIcon" : initSimpleData.dappIcon,
            "scheme" : initSimpleData.scheme,
            "disableInstall" : initSimpleData.disableInstall
        ]
        
        presentDataView(data: initDict.toJsonString() ?? "")
    }
    
}

//MARK: -
extension ExampleListViewController{
    //MARK: SW认证
    func simpleWalletAuthori(){
        let requestData = AuthorizeProtocolRequest()
        requestData.protocol = "SimpleWallet"
        requestData.version = "1.0"
        requestData.action = WalletActionConstants.LOGIN.rawValue
        requestData.dappName = "KEY Swap"
        requestData.dappIcon = "https://oss02.bihu.com/head/1866a59f45432215a4f71f5dd08856d4.jpeg?x-oss-process=style/size_head"
        requestData.dappUserName = "KEY Swap"
        requestData.uuID = "123456"
        requestData.loginUrl = "http://115.159.101.119:8080/app/mock/17/sdk/action/callback"
        requestData.loginMemo = "KEYSwap-秋水共长天一色"
        
        MYKEYWallet.shared.redirectToMYKEYWithSimpleWallet(baseRequest: requestData, response: MYKEYResponse(success: { (response) in
            print(response)
            self.presentDataView(data: response)
        }, failure: { (errorValue) in
            self.presentDataView(data: errorValue)
        }, cancelled: {
            var style = ToastStyle()
            style.backgroundColor = UIColor.green
            self.view.makeToast("取消了。。", title: nil, image: nil, style: style, completion: nil)

        }))
        
    }
    
    //MARK: SW-Contract
    func simpleWalletContract(){
        
     
        /*
        "symbol" : "BLACK",
        "code" : "mykeytoken12",
        "decimals" : 4,
        */
        
        let transferData = TransferData()
        transferData.from = ""
        transferData.to = "aaaaaooooo12"
        transferData.quantity = "0.0301 BLACK"
        transferData.memo = "transferAction-memo"
        
        let transferActionData = TransferAction()
        transferActionData.account = "mykeytoken12"
        transferActionData.name = WalletActionConstants.TRANSFER.rawValue
        transferActionData.info = "transferAction-info"
        transferActionData.data = transferData

        let contractActionData = ContractAction()
        contractActionData.account = "mykeytoken12"
        contractActionData.name = "buyram"
        contractActionData.info = "购买内存"
        let dict = ["payer":"ppppphhhhh12","receiver":"aaaaaooooo12","quant":"0.0011 BLACK"]
        contractActionData.data = dict.toJsonString() ?? ""
        
        let contractData = ContractProtocolRequest()
        contractData.protocol = "SimpleWallet"
        contractData.version = "1.0"
        contractData.action = WalletActionConstants.TRANSACTION.rawValue
        contractData.dappName = "KEY Swap"
        contractData.dappIcon = "https://oss02.bihu.com/img/b0e293f3b0dadf815ff8de5fbd5446e3.jpg?x-oss-process=style/size_head"
        contractData.contractUrl = "http://115.159.101.119:8080/app/mock/17/sdk/action/callback"
        contractData.desc = "执行REX抵押操作"

        contractData.actions = [transferActionData,contractActionData]

        MYKEYWallet.shared.redirectToMYKEYWithSimpleWallet(baseRequest: contractData, response: MYKEYResponse(success: { (responseValue) in
            self.presentDataView(data: responseValue)
        }, failure: { (errorValue) in
            self.presentDataView(data: errorValue)
            self.view.makeToast("无话可说，竟然出错了！")
        }, cancelled: {
            var style = ToastStyle()
            style.backgroundColor = UIColor.green
            self.view.makeToast("取消了。。", title: nil, image: nil, style: style, completion: nil)
        }))
    }
    
    //MARK: SW转账
    func simpleWalletTransfer(){
        
        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */
        
        let transferRequest = TransferProtocolRequest()
        transferRequest.protocol = ConfigConstants.SIMPLE_WALLET_PROTOCOL.rawValue
        transferRequest.version = ConfigConstants.MYKEY_WALLET_VERSION.rawValue
        transferRequest.dappName = "DappA"
        transferRequest.dappIcon = "https://oss02.bihu.com/img/60639a5a39174590561d32d10a899bda.png?x-oss-process=style/size_head"
        transferRequest.action = WalletActionConstants.TRANSFER.rawValue
        transferRequest.from = ""
        transferRequest.to = "aaaaaooooo12"
        transferRequest.amount = 0.0201
        transferRequest.contract = "mykeytoken12"
        transferRequest.symbol = "BLACK"
        transferRequest.precision = 4
        transferRequest.dappData = "memo-infomation"
        transferRequest.desc = "comment-description"
        
        MYKEYWallet.shared.redirectToMYKEYWithSimpleWallet(baseRequest: transferRequest, response: MYKEYResponse.init(success: { (responseValue) in
            self.presentDataView(data: responseValue)
            self.view.makeToast("success")

        }, failure: { (errorValue) in
            self.presentDataView(data: errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }
    
    //MARK: SW签名
    func simpleWalletSig(){
        let signData = SignProtocolRequest()
        signData.protocol = ConfigConstants.SIMPLE_WALLET_PROTOCOL.rawValue
        signData.version = "1.0"
        signData.action = WalletActionConstants.SIGN.rawValue
        signData.dappName = "SEER"
        signData.dappIcon = "https://oss02.bihu.com/img/a34b1be880dc00038466fe68954ffe51.jpg?x-oss-process=style/size_head"
        signData.message = "unSignData-eg-aaaaaxxxxxxxmmmmmm"
        signData.signUrl = "http://115.159.101.119:8080/app/mock/17/sdk/action/callback"
        
        MYKEYWallet.shared.redirectToMYKEYWithSimpleWallet(baseRequest: signData, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)

        }, failure: { (errorValue) in
            self.presentDataView(data: errorValue)

        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }
    
    //MARK: MYKEY认证
    func mykeyAuthorize(){
        
        let authorizeRequest = AuthorizeRequest()
        authorizeRequest.userName = "哆啦A梦"
        authorizeRequest.info = "Test information"
        authorizeRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        
        MYKEYWallet.shared.authorize(authorizeRequest: authorizeRequest, response: MYKEYResponse.init(success: { (response) in
            self.view.makeToast("success")
            print("response")
            print(response)
        }, failure: { (errorValue) in
            self.view.makeToast("failure")
            print("errorValue")
            print(errorValue)
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
      
    }
    
    //MARK: MYKEY-Contract
    func mykeyContract(){

        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */
        
        let transferData = TransferData()
        transferData.from = ""
        transferData.to = "aaaaaooooo12"
        transferData.quantity = "0.0308 BLACK"
        transferData.memo = "转账啊memo"
        
        let transferActionData = TransferAction()
        transferActionData.account = "mykeytoken12"
        transferActionData.name = WalletActionConstants.TRANSFER.rawValue
        transferActionData.info = "没事转个账玩玩"
        transferActionData.data = transferData
        
        let contractActionData = ContractAction()
        contractActionData.account = "mykeytoken12"
        contractActionData.name = "buyram"
        contractActionData.info = "购买内存"
        let dict = ["payer":"ppppphhhhh12","receiver":"aaaaaooooo12","quant":"0.0101 BLACK"]
        contractActionData.data = dict.toJsonString() ?? ""

        let contractRequest = ContractRequest()
        contractRequest.info = "Perform the mortgage REX operation"
        contractRequest.orderId = "BH19004"
        contractRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        contractRequest.actions = [transferActionData,contractActionData]

        MYKEYWallet.shared.contract(contractRequest: contractRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)

        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
        
    }
    
    //MARK: MYKEY转账
    func mykeyTransfer(){
        
        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */
        
        let transferRequest = TransferRequest()
        transferRequest.from = ""
        transferRequest.to = "aaaaaooooo12"
        transferRequest.amount = 0.02
        transferRequest.memo = "tansfer.memo"
        transferRequest.contractName = "mykeytoken12"
        transferRequest.symbol = "BLACK"
        transferRequest.info = "lol,transfer-information,lol"
        transferRequest.decimal = 4
        transferRequest.orderId = "BH19004"
        transferRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        
        MYKEYWallet.shared.transfer(transferRequest: transferRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)

        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
        
    }
    
    //MARK: MYKEY签名
    func mykeySign(){
        let signRequest = SignRequest()
        signRequest.message = "Messages that need to be signed, [it could be random which come from dapp server]"
        signRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/asset/check_account"
        
        MYKEYWallet.shared.sign(signRequest: signRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)
        }, failure: { (errorValue) in
            self.view.makeToast("failure")
            print("errorValue")
            print(errorValue)
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
        
        let s1 = ErrorCode.cannotWakeUp.rawValue
        print(s1)

    }
    
    /*
     "symbol" : "BLACK",
     "code" : "mykeytoken12",
     "decimals" : 4,
     */
    
    //MARK: 获取账户余额
    func getTokenBalance(){
        MYKEYWallet.shared.getBalance(chain: "EOS", code: "mykeytoken12", symbol: "BLACK", completionHandler: MYKEYApiResponse(success: { (response) in
            print("getTokenBalanceResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:")
            self.presentDataView(data: response)
            print(response)

        }, failure: { (errorValue) in
            self.view.makeToast(errorValue)
            print("getTokenBalanceResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<:")
            print(errorValue)
        }))
        
    }
    
    //MARK: 解锁中的列表
    func getUnlockList(){
        MYKEYWallet.shared.getUnlockList(chain: "EOS", code: "mykeytoken12", symbol: "BLACK", completionHandler: MYKEYApiResponse(success: { (response) in
            print("getUnlockListResult:")
            self.presentDataView(data: response)
            print(response)

        }, failure: { (errorValue) in
            self.view.makeToast(errorValue)
            print("getTokenBalanceResult:")
            print(errorValue)

        }))
    }
    
    // MARK: 获取绑定信息
    func getBoundInfo(){
        MYKEYWallet.shared.getBoundInfo(completionHandler: MYKEYApiResponse(success: { (response) in
            print("getBoundInfoResult:")
            self.presentDataView(data: response)
            print(response)
            
        }, failure: { (errorValue) in
            self.view.makeToast(errorValue)
            print("getBoundInfoResult:")
            print(errorValue)

        }))
    }
    
    //MARK: 锁仓
    /// 固定传值 name=stake
    func stake(){
        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */

        let contractActionData = ContractAction()
        contractActionData.account = "hellomykey11"
        contractActionData.name = "stake"
        contractActionData.info = "stake-info"
        let dict = ["owner":"aaaaaooooo54","quantity":"0.0101 ADD"]
        contractActionData.data = dict.toJsonString() ?? ""
        
        let contractRequest = ContractRequest()
        contractRequest.info = "Perform the mortgage REX operation"
        contractRequest.orderId = "BH19004"
        contractRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        contractRequest.actions = [contractActionData]
        
        MYKEYWallet.shared.contract(contractRequest: contractRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)
            
        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }
    
    //MARK: 解锁
    /// 固定传值： name=unstake
    func unStake(){
        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */
        
        let contractActionData = ContractAction()
        contractActionData.account = "mykeytoken12"
        contractActionData.name = "unstake"
        contractActionData.info = "购买内存"
        let dict = ["payer":"ppppphhhhh12","receiver":"aaaaaooooo12","quant":"0.0101 BLACK"]
        contractActionData.data = dict.toJsonString() ?? ""
        
        let contractRequest = ContractRequest()
        contractRequest.info = "Perform the mortgage REX operation"
        contractRequest.orderId = "BH19004"
        contractRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        contractRequest.actions = [contractActionData]
        
        MYKEYWallet.shared.contract(contractRequest: contractRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)
            
        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }

    //MARK: 普通转账
    func customTransfer(){
        mykeyTransfer()
    }

    //MARK: 转账并锁仓
    /// 固定传值：memo = "Transfer:FromStakedToLiquid"
    func transferWithStake(){
        /*
         "symbol" : "BLACK",
         "code" : "mykeytoken12",
         "decimals" : 4,
         */
        
        let transferRequest = TransferRequest()
        transferRequest.from = ""
        transferRequest.to = "aaaaaooooo12"
        transferRequest.amount = 0.02
        transferRequest.memo = "Transfer:FromStakedToLiquid"
        transferRequest.contractName = "mykeytoken12"
        transferRequest.symbol = "BLACK"
        transferRequest.info = "FromStakedToLiquid"
        transferRequest.decimal = 4
        transferRequest.orderId = "BH19004"
        transferRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        
        MYKEYWallet.shared.transfer(transferRequest: transferRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)
            
        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }

    //MARK: 解锁并转账
    /// 固定传值： memo = "Transfer:FromLiquidToStaked"
    func unStakeWithTransfer(){
        let transferRequest = TransferRequest()
        transferRequest.from = ""
        transferRequest.to = "aaaaaooooo12"
        transferRequest.amount = 0.02
        transferRequest.memo = "Transfer:FromLiquidToStaked"
        transferRequest.contractName = "mykeytoken12"
        transferRequest.symbol = "BLACK"
        transferRequest.info = "FromLiquidToStaked"
        transferRequest.decimal = 4
        transferRequest.orderId = "BH19004"
        transferRequest.callbackUrl = "http://115.159.101.119:8080/app/mock/17/account/dapp/link"
        
        MYKEYWallet.shared.transfer(transferRequest: transferRequest, response: MYKEYResponse.init(success: { (response) in
            self.presentDataView(data: response)
            self.view.makeToast("success")
            print("response")
            print(response)
            
        }, failure: { (errorValue) in
            print("errorValue")
            print(errorValue)
            self.view.makeToast("failure")
        }, cancelled: {
            self.view.makeToast("cancelled")
        }))
    }

    func presentDataView(data : String){
        
        let controller = ResultViewController()
        controller.data = data
        self.present(controller, animated: true, completion: nil)
        
    }

}

