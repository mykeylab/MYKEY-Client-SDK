//
//  ViewController.m
//  MYKEY-SDK-Demo-OC
//
//  Created by mykeydev on 2019/7/20.
//  Copyright © 2019 MYKEYLab. All rights reserved.
//

#import "ViewController.h"
#import "MYKEY-SDK-Demo-OC-Bridging-Header.h"

@interface ViewController ()
    
    @end

@implementation ViewController
    
- (void)viewDidLoad {
    [super viewDidLoad];
    
    UILabel * titleLabel = [[UILabel alloc] init];
    titleLabel.frame = CGRectMake(20, 64, 200, 30);
    titleLabel.text = @"MYKEY Demo";
    titleLabel.textColor = [UIColor blackColor];
    titleLabel.font = [UIFont systemFontOfSize:24.0];
    [self.view addSubview:titleLabel];
    
    UIButton * initBtn = [[UIButton alloc] init];
    initBtn.frame = CGRectMake(20, 104, 100, 30);
    [initBtn setTitle:@"Simple初始化" forState:UIControlStateNormal];
    [initBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    initBtn.titleLabel.font = [UIFont systemFontOfSize:16];
    initBtn.backgroundColor = [UIColor lightGrayColor];
    [initBtn addTarget:self action:@selector(initMYKEYSimpleWallet) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:initBtn];
    
    UIButton * bindBtn = [[UIButton alloc] init];
    bindBtn.frame = CGRectMake(20, 144, 100, 30);
    [bindBtn setTitle:@"绑定账户" forState:UIControlStateNormal];
    [bindBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    bindBtn.titleLabel.font = [UIFont systemFontOfSize:16];
    bindBtn.backgroundColor = [UIColor lightGrayColor];
    [bindBtn addTarget:self action:@selector(bindMYKEY) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:bindBtn];
    
}
    
    //MARK: MYKEY初始化
    -(void)initMYKEYSimpleWallet{
        NSLog(@"initMYKEYWallet");
        
        InitSimpleRequest * mkRequest = [[InitSimpleRequest alloc] init];
        mkRequest.dappName = @"SDKOCDemo";
        mkRequest.dappIcon = @"https://oss02.bihu.com/img/b4e2dcd644c322b5bc9584082f0c5ff9.png?x-oss-process=style/size_head";
        mkRequest.scheme = @"ocdemoscheme";
        
        [MYKEYWallet.shared initWalletSimpleWithInitSimpleData:mkRequest];
        
    }
    
    //MARK: MYKEY认证
    -(void)bindMYKEY{
        NSLog(@"bindMYKEY");
        AuthorizeRequest * authRequest = [[AuthorizeRequest alloc] init];
        authRequest.userName = @"哆啦A梦";
        authRequest.info = @"authorize-infomation";
        authRequest.callbackUrl = @"http://115.159.101.119:8080/app/mock/17/account/dapp/link";
        
        [MYKEYWallet.shared authorizeWithAuthorizeRequest:authRequest response:[[MYKEYResponse alloc] initWithSuccess:^(NSString * responseValue) {
            NSLog(@"Success-responseValue");
            NSLog(@"%@", responseValue);
        } failure:^(NSString * errorValue) {
            NSLog(@"failure-errorValue");
            NSLog(@"%@", errorValue);
        } cancelled:^{
            NSLog(@"cancelled");
        }]];
        
    }
    
    
    @end
