# project-registry-backend
Spring backend for Project Registry

# Configuration for Checkstyle Plugin

To facilitate ease of development according to the enforced styleguide, please make the following changes within Eclipse (if needed)

1. Install `Checkstyle` Plugin
    - Go to `Help > Eclipse Marketplace...`
    - Search for `Checkstyle`
    - Install `Checkstyle Plug-in`
    - Restart Eclipse
    - Note that Eclipse will often detect this plugin as required and prompt you to install automatically after the project is imported
2. Import `Existing Maven Project` into Eclipse for this application
3. Change Formatter to use spaces instead of tabs
    - Click `Window > Preferences`
    - Expand `Java > Code Style > Formatter`
    - Click `Edit`
    - Expand `Indentation`
    - Change `Tab Policy` to `Spaces Only`
      - Verify that `Indentation size` and `Tab size` are both set to 4
    - Optionally, you can change the profile name
      - This will create a new Formatter profile to make it easy to swap back to tabs for other projects
    - Click `OK` and `Apply and Close`

Now you should be able to use `Ctrl + Shift + F` and `Ctrl + Shift + O` to format the style and organize the imports according to the enforced style guide.