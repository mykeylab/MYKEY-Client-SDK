//
//  ResultViewController.swift
//  MYKEY-SDK-Demo-iOS
//
//  Created by mykeylab on 2019/7/1.
//  Copyright © 2019 MYKEY. All rights reserved.
//

import Foundation
import UIKit

class ResultViewController: UIViewController {

    private lazy var textView : UITextView = {
        let view = UITextView(frame: CGRect(x: 20, y: 40, width: kscWidth - 40, height: kscHeight - 180))
        view.backgroundColor = UIColor.white
        view.textColor = UIColor.black
        view.font = UIFont.systemFont(ofSize: 14.0)
        view.isEditable = false
        view.isUserInteractionEnabled = false
        view.clipsToBounds = true
        return view
    }()
    
    private lazy var backBtn : UIButton = {
        let btn = UIButton(frame: CGRect(x: 50, y: kscHeight - 100, width: kscWidth - 100, height: 44))
        btn.setTitle("返回", for: .normal)
        btn.titleLabel?.font = UIFont.systemFont(ofSize: 16.0)
        btn.setTitleColor(UIColor.black, for: .normal)
        btn.addTarget(self, action: #selector(self.backEvent), for: .touchUpInside)
        btn.backgroundColor = UIColor.white
        return btn
    }()
    
    var data : String?
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)

        if let dataValue = data?.data(using: .utf8){
            
            do{
                
                let jsonValue = try JSONSerialization.jsonObject(with: dataValue, options: JSONSerialization.ReadingOptions.allowFragments)
                var jsonDict = (jsonValue as? Dictionary<String,Any>) ?? Dictionary<String,Any>()
                if let payloadString = (jsonDict["payload"] as? String){
                    if let payloadData = payloadString.data(using: .utf8) {
                        let payloadDict = try JSONSerialization.jsonObject(with: payloadData, options: .allowFragments)
                        jsonDict["payload"] = payloadDict
                    }
                }
                
                let tempData = try JSONSerialization.data(withJSONObject: jsonDict, options: .prettyPrinted)
                textView.text = String(data: tempData, encoding: .utf8)
            }catch let error{
                print(error.localizedDescription)
            }
            
        }
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor.lightGray
        view.addSubview(textView)
        view.addSubview(backBtn)
        
    }
    
    @objc func backEvent(){
        self.dismiss(animated: true, completion: nil)
    }
    
    deinit {
        print(#function)
        print(self.textView)
    }

    
}
