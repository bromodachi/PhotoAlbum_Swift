//
//  listOfAlbums.swift
//  PhotoAlbum_swift
//
//  Created by Conrado on 2014/12/26.
//  Copyright (c) 2014年 Conrado. All rights reserved.
//

import UIKit

extension UIPopoverController {
    class var _popoversDisabled : Bool {
        get { return false }
    }
}


class listOfAlbums: UITableViewController {

    var albums: Array<Album> =  []
    var alert: UIAlertController!
    
    var popController:UITableViewController =   PopController()
    
    var makePrivateBool:Bool = false
    

    @IBOutlet weak var addAlbum: UIButton!


    @IBOutlet weak var showMenuPop: UIBarButtonItem!



    override func viewDidLoad() {
        super.viewDidLoad()
        albums = [Album]()
        viewDidAppear(false)
        popController.view.hidden=true
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Potentially incomplete method implementation.
        // Return the number of sections.
        return 1
    }
    
   
  

    /*let playerInformationViewController =  listMenu()
    playerInformationViewController.preferredContentSize = CGSizeMake(100, 100)
    
    
    var popController = UIPopoverController(contentViewController: playerInformationViewController)
    
    popController.popoverContentSize = CGSizeMake(100, 100)
    popController.presentPopoverFromRect(CGRectMake(0, 0, 48, 37), inView: self.view, permittedArrowDirections: UIPopoverArrowDirection.Down, animated: true)
    presentViewController(playerInformationViewController, animated: true, completion: nil)*/
    
/*var popMe:UITableViewController=listMenu()
var popController = UIPopoverController(contentViewController: popMe)
popController.presentPopoverFromRect(sender.frame, inView: self.view, permittedArrowDirections: UIPopoverArrowDirection.Any, animated: true)*/
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete method implementation.
        // Return the number of rows in the section.
        return albums.count
    }

    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let CellId: String = "Cell"
       
        var cell: CustomCell = tableView.dequeueReusableCellWithIdentifier(CellId) as CustomCell
        //cell.textLabel?.text = self.albums[indexPath.row].getAlbumName()
        var get: Album = albums[indexPath.row]
        cell.setAlbumNameLabel( get.getAlbumName())
        cell.setPreviewImage()
        //TODO: Move this in the custom cell
        let stringNum:String = "# Photos:"
        let albumNum:String = String(get.getNumOfPhotos())
        let finalString = stringNum+" " + albumNum
        cell.setNumberOfPhotos( finalString)
        return cell
    }
    
    
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the specified item to be editable.
        return true
    }
    
    
   /* @IBAction func showAddDialog(sender: UIButton) {
        viewDidAppear(true )
    }*/
    
    @IBAction func showAddDialog(sender: UIButton) {
        viewDidAppear(true )
    }
    func addAlbumWithoutPassword(albumName:String){
        
        var add:Album =  Album(AlbumName: albumName)
        doTheActualAdding(add)
        
    }
    
    func addAlbumWithPassword(albumName:String,pass1:String, pass2:String){
        if ( pass1 != pass2||(pass1.isEmpty || pass2.isEmpty)){
            println("Not adding")
        }
            //I forgot what the other case was.
        else{
            var add:Album = Album(AlbumName: albumName, pass: pass1)
             doTheActualAdding(add)
        }
      
    }
    
  
    func doTheActualAdding(add:Album){
        if let i = find(albums, add){
            //show error dialog
            println("already have this album name")
        }
        else{
            
            albums.append(add)
            self.tableView.reloadData()
        }
    }
    
    
    
    @IBAction func showMenuItemPopUp(sender: UIBarButtonItem) {
        let popController =   PopController()
        let test = popController.view
        test.frame = CGRectMake( 200, 200, 100, 100 );
        self.view.addSubview(test)
        self.view.bringSubviewToFront(test)
       
       /* var popController = UIPopoverController(contentViewController: playerInformationViewController)
        
        popController.popoverContentSize = CGSize(width: 50, height: 50)
        popController.contentViewController.preferredContentSize =  CGSizeMake(320, 550)
        popController.presentPopoverFromBarButtonItem(sender, permittedArrowDirections: UIPopoverArrowDirection.Up, animated: true)*/
    }
    //prompt the user to input a album name
    override func viewDidAppear(animated: Bool) {
        makePrivateBool=false
        var textField:UITextField = UITextField()
        alert = UIAlertController(title: "Album Name",
            message: "Please enter the name you want this album to be called. Switch the slider if you want this album to be private & enter a password",
            preferredStyle: UIAlertControllerStyle.Alert)
        
        //Add a text field for the album name
        alert.addTextFieldWithConfigurationHandler({(textField) in
            textField.placeholder = "Enter album name:"
        })
        
        //create spinner to make it private or not.
        //TODO: See if you can actually place the spinner in a good spot
        var makePrivate = UISwitch()
        //make it non-private first
        makePrivate.on = false
        makePrivate.addTarget(self, action: "makePrivateAlbum:", forControlEvents:  .ValueChanged)
        
        //hanldes the adding
        let addAlbumAction = UIAlertAction(title: "add", style: .Default) { (_) in
            let addAlbumTextField = self.alert.textFields![0] as UITextField
            println(addAlbumTextField.text)
            
            //check if the user added an empty string
            var isEmpty:String = addAlbumTextField.text
            var trimedString:String = isEmpty.stringByTrimmingCharactersInSet(NSCharacterSet.whitespaceAndNewlineCharacterSet())
            let counter = countElements(trimedString)
            if(!( counter>0)){
                println("Empty String. Will not add")
            }
            else{
                if(self.makePrivateBool){
                    let pass1TextField = self.alert.textFields![1] as UITextField
                    let pass2TextField = self.alert.textFields![2] as UITextField
                    self.addAlbumWithPassword(addAlbumTextField.text,  pass1: pass1TextField.text,  pass2: pass2TextField.text)
                }
                else{
                    self.addAlbumWithoutPassword(addAlbumTextField.text)
                }
            }
        }
        
        //password for album
        alert.addTextFieldWithConfigurationHandler { (textField) in
            textField.placeholder = "Password"
            textField.secureTextEntry = true
            textField.enabled = false
        }
        
        alert.addTextFieldWithConfigurationHandler { (textField) in
            textField.placeholder = "Password Confirmation"
            textField.secureTextEntry = true
             textField.enabled = false
        }
        
        
        //add action
        alert.addAction(addAlbumAction)
        self.alert.view.addSubview(makePrivate)
        
        //also a cancel, in case the user chanes his mind
        alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertActionStyle.Default, handler: nil))
        //TODO: must be a better way to do this
        if (animated){
            presentViewController(alert, animated: animated, completion: nil)
        }
    }
    
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            
            
            albums.removeAtIndex(indexPath.row)
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    
    func makePrivateAlbum(sender:UISwitch){
    
        let pass1TextField = self.alert.textFields![1] as UITextField
        let pass2TextField = self.alert.textFields![2] as UITextField
        if(sender.on==true){
            makePrivateBool = true
            println(makePrivateBool)
            pass1TextField.enabled=true
            pass2TextField.enabled=true
        }
        else{
            makePrivateBool = false
            pass1TextField.enabled=false
            pass2TextField.enabled=false
        }
    }
    
    
    
    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {
    
    }
    */
    
    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
    // Return NO if you do not want the item to be re-orderable.
    return true
    }
    */
    
    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    }
    */
    
}