//
//  CustomCell.swift
//  PhotoAlbum_swift
//
//  Created by Conrado on 2014/12/26.
//  Copyright (c) 2014年 Conrado. All rights reserved.
//

import UIKit

class CustomCell: UITableViewCell {

    @IBOutlet weak var albumPreview: UIImageView!
    @IBOutlet weak var albumName: UILabel!
    @IBOutlet weak var dateRange: UILabel!
    @IBOutlet weak var howManyPhotos: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func setAlbumNameLabel(albumName:String){
        self.albumName.text = albumName
    }
    
    func setNumberOfPhotos(numOfPhotos:String){
        self.howManyPhotos.text =  String(numOfPhotos)
    }
    
    func setPreviewImage(){
            self.albumPreview.image = UIImage(named: "hiroshi.png")
        
    }

}
