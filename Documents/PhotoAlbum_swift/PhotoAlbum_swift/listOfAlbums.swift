//
//  listOfAlbums.swift
//  PhotoAlbum_swift
//
//  Created by Conrado on 2014/12/26.
//  Copyright (c) 2014年 Conrado. All rights reserved.
//

import UIKit

extension Array {
    mutating func removeAtIndexes (ixs:[Int]) -> () {
        for i in ixs.sorted(>) {
            self.removeAtIndex(i)
        }
    }
}
//TO DO: Multiple edits works but doesn't when you're trying to switch names. Fix this?
//Clean up code

class listOfAlbums: UITableViewController{
    
    var albums: Array<Album> =  []
    var alert: UIAlertController!
    var optionChoices:[String] = ["Rename", "Delete Multiple"]
    
    var makePrivateBool:Bool = false
    
    var doWork:Bool = false
    var completeActivated:Bool = false
    var index:Int = -1
    
    var dialog:UITableView!
    
    
    @IBOutlet weak var addAlbum: UIButton!
    
    //hold more buttons on the right
    var buttonDictionary: [UIBarButtonItem]!
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        albums = [Album]()
        viewDidAppear(false)
        createDialog()
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }
    
    func createDialog(){
        let frame = CGRectMake(200, 0, 200, 600);
        dialog = UITableView(frame: frame)
        dialog.bounds = frame
        dialog.setTranslatesAutoresizingMaskIntoConstraints(false)
        dialog.autoresizingMask = UIViewAutoresizing.FlexibleRightMargin
        self.view.addSubview(dialog)
        dialog.hidden = true
        dialog.registerClass(UITableViewCell.self, forCellReuseIdentifier: "cell")
        dialog.delegate = self;
        dialog.dataSource = self;
        
        self.tableView.reloadData()
        
        
    }
    func addButtons(){
        let select: Selector = "showOptions:"
        /*let button1 = UIBarButtonItem(title: "完成", style: UIBarButtonItemStyle.Plain, target: self, action: select)
        let button2 = UIBarButtonItem(title: "World", style:  UIBarButtonItemStyle.Plain, target: self, action: select)*/
        let button1 = UIBarButtonItem(title: "|||", style:  UIBarButtonItemStyle.Plain, target: self, action: select)
        buttonDictionary = [button1]
        let buttonArray:NSArray =  buttonDictionary
        //  view.addConstraints(view_constraint_V)
        self.navigationItem.rightBarButtonItems = buttonArray
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
        if(tableView == self.view){
            return albums.count
        }
        else{
            return optionChoices.count
        }
    }
    func deleteMultipleAlbums(add:Array<Album>, path:Array<NSIndexPath>){
        
        for getAlbum:Album in add{
            if let index = find(albums, getAlbum){
                albums.removeAtIndex(index)
            }
        }

        self.tableView.deleteRowsAtIndexPaths(path, withRowAnimation: UITableViewRowAnimation.Automatic)
        
        dispatch_async(dispatch_get_main_queue()) {
            self.tableView.reloadData()
            
        }
        
    }
    
    @IBAction func showOptions(sender: UIBarButtonItem) {
        if(sender.title == "|||"){
            if(dialog.hidden == false){
                dialog.hidden  = true
            }
            else{
                dialog.hidden  = false
            }
           
        }
        else if(sender.title == "完成"){
            
            dialog.hidden  = true
            completeActivated = true
            doWork = false
            dispatch_async(dispatch_get_main_queue()) {
                self.tableView.reloadData()
                
            }
            
            
        }
        else if(sender.title == "削除する"){
            println(self.tableView.indexPathForSelectedRow())
            println(self.tableView.indexPathForSelectedRow()?.length)
            if(self.tableView.indexPathForSelectedRow()?.length == nil){
                //do nothing 
                buttonDictionary[0].title = "|||"
                addAlbum.hidden = false
                self.tableView.setEditing(false, animated: true)
                
                self.tableView.allowsMultipleSelectionDuringEditing = false
            }
            else{
               var arrayOfAlbums: Array<Album> = [Album]()
                var arrayOfIndexPaths: Array<NSIndexPath> = [NSIndexPath]()
                if let indices:NSArray = self.tableView.indexPathsForSelectedRows(){
                    println("I should be deleting")
                    
                    for var i = 0 ;  i < indices.count ; ++i{
                        var indexPath = indices.objectAtIndex(i) as NSIndexPath
                        
                        var remove:Album = albums[indexPath.row]
                        arrayOfAlbums.append(remove)
                        arrayOfIndexPaths.append(indexPath)
                    }
                   deleteMultipleAlbums(arrayOfAlbums, path: arrayOfIndexPaths)
                 /*if let indices:NSArray = self.tableView.indexPathsForSelectedRows(){
                    for (index, value) in enumerate(indices) {
                        println(value.row)
                        //albums.removeAtIndex(value.row)
                        let x = value as NSIndexPath
                        println(value.indexPath)
                        
                        if(albums.count == 0){
                            buttonDictionary[0].title = ""
                        }
                    }
                    self.tableView.deleteRowsAtIndexPaths(indices, withRowAnimation: UITableViewRowAnimation.Automatic)*/
                    self.tableView.setEditing(false, animated: true)

                    self.tableView.allowsMultipleSelectionDuringEditing = false
                    addAlbum.hidden = false
                    
                
                   
                
                }
                else{
                    println("fucked up")
                }
            }
        }

        else{
            dialog.hidden  = true
        }
        
    }
    
    //hide the options
    override func touchesBegan(touches: NSSet, withEvent event: UIEvent) {
        
        
    }
    
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let CellId: String = "Cell"
        
        
        if(tableView == self.view){
            var cell: CustomCell = tableView.dequeueReusableCellWithIdentifier(CellId) as CustomCell
            //cell.textLabel?.text = self.albums[indexPath.row].getAlbumName()
            println("How many items in albums?")
            println(albums.count)
            println("Int in rows?")
            println(indexPath.row)
            
            var get: Album = albums[indexPath.row]
            cell.setAlbumNameLabel( get.getAlbumName())
            cell.setAlbumNameEditable(false, replaceText: false)
            if(doWork){
                println(index)
                cell.setAlbumNameEditable(true, replaceText: false)
                /*   if(!(index == -1)){
                if(index == indexPath.row){
                cell.setAlbumNameEditable(true)
                }
                
                }*/
                // doWork = false
                // index = -1
            }
            if(completeActivated){
                
                addAlbum.hidden = false
                var add:Album =  Album(AlbumName: cell.albumEditable.text)
                if let i = find(albums, add){
                    //show error dialog
                    println("already have this album name")
                    cell.setAlbumNameEditable(false, replaceText: false)
                }
                else{
                    cell.setAlbumNameEditable(false, replaceText: true)
                    get.setAlbumName(cell.albumEditable.text)
                    println("testing: what name: ")
                    println(cell.albumEditable.text)
                    cell.setAlbumNameLabel( get.getAlbumName())
                }
                buttonDictionary[0].title = "|||"
            }
            let counter = albums.count - 1
            if(counter == indexPath.row && completeActivated == true){
                completeActivated = false
            }
            cell.setPreviewImage()
            //TODO: Move this in the custom cell
            let stringNum:String = "# Photos:"
            let albumNum:String = String(get.getNumOfPhotos())
            let finalString = stringNum+" " + albumNum
            cell.setNumberOfPhotos( finalString)
            return cell
        }
        else{
            let cell = tableView.dequeueReusableCellWithIdentifier("cell", forIndexPath: indexPath) as UITableViewCell
            cell.textLabel?.text = optionChoices[indexPath.row]
            return cell
        }
    }
    
    
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return NO if you do not want the specified item to be editable.
        return true
    }
    //did select method
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        if(tableView == self.view){
            dialog.hidden = true
            /*if(doWork){
            makeAlbumsEditable(indexPath)
            index = indexPath.row
            }*/
            
        }
        else {
            
            let choice = optionChoices [ indexPath.row]
            if(choice == "Rename"){
                dialog.hidden = true
                doWork=true
                addAlbum.hidden = true
                let select: Selector = "showOptions:"
                buttonDictionary[0].title = "完成"
                
                
                dispatch_async(dispatch_get_main_queue()) {
                    self.tableView.reloadData()
                }
            }
            else if (choice == "Delete Multiple"){
                
                self.tableView.setEditing(true, animated: true)
                dialog.hidden = true
                addAlbum.hidden = true
                buttonDictionary[0].title="削除する"
                self.tableView.allowsMultipleSelectionDuringEditing = true

            }
            
        }
    }
    
    func makeAlbumsEditable(indexPath: NSIndexPath){
        println("here in album editable")
        let CellId: String = "Cell"
        index = indexPath.row
        var cell: CustomCell = tableView.dequeueReusableCellWithIdentifier(CellId) as CustomCell
        cell.setAlbumNameEditable(true, replaceText: false)
        dispatch_async(dispatch_get_main_queue()) {
            self.tableView.reloadData()
        }
        
        
        
    }
    
    
    
    /* @IBAction func showAddDialog(sender: UIButton) {
    viewDidAppear(true )
    }*/
    
    @IBAction func showAddDialog(sender: UIButton) {
        dialog.hidden = true
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
            addButtons()
            self.tableView.reloadData()
        }
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
            if(albums.count == 0){
                buttonDictionary[0].title = ""
            }
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
    
    /*For options menu's table*/
    
    
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