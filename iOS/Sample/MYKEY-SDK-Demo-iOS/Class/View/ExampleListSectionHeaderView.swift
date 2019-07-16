//
//  ExampleListSectionHeaderView.swift
//  TestSDK
//
//  Created by mykeylab on 2019/6/19.
//  Copyright © 2019 MYKEY. All rights reserved.
//

import Foundation
import UIKit
class ExampleListSectionHeaderView: UICollectionReusableView {
    
    lazy var titleLabel : UILabel = {
        let label = UILabel()
        label.textColor = UIColor.black
        label.font = UIFont.boldSystemFont(ofSize: 16.0)
        label.textAlignment = NSTextAlignment.left
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.frame = CGRect(x: 0, y: 0, width: kscWidth, height: 40)
        titleLabel.frame = CGRect(x: 20, y: 0, width: kscWidth, height: 40)
        addSubview(titleLabel)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

