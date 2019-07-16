//
//  ExampleListCell.swift
//  TestSDK
//
//  Created by mykeylab on 2019/6/19.
//  Copyright © 2019 MYKEY. All rights reserved.
//

import Foundation
import UIKit
class ExampleListCell: UICollectionViewCell {
    
    lazy var titleLabel : UILabel = {
        let label = UILabel()
        label.textColor = UIColor.black
        label.font = UIFont.systemFont(ofSize: 16.0)
        label.textAlignment = NSTextAlignment.center
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        contentView.backgroundColor = UIColor.lightGray
        
        titleLabel.frame = contentView.bounds
        contentView.addSubview(titleLabel)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
