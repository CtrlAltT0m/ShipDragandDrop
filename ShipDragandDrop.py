import os
import tkinter as tk


class Maritime(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Sea Simulation")
        self.attributes("-fullscreen", True)  # Set the window to fullscreen mode

        # Create the blue square background
        self.sea_panel = tk.Canvas(self, bg="blue")
        self.sea_panel.pack(fill=tk.BOTH, expand=True)

        # Create the units frame
        self.units_frame = tk.LabelFrame(self.sea_panel, text="Units:", bg="white")
        self.units_frame.pack(side=tk.BOTTOM, pady=10)

        # Create the navy ships, submarines, and aircraft icons
        self.navy_icons = []
        self.create_icon("C:\\Dev\\destroyer_icon.png")
        self.create_icon("C:\\Dev\\aircraft_icon.png")
        self.create_icon("C:\\Dev\\amphibious_icon.png")
        self.create_icon("C:\\Dev\\carrier_icon.png")
        self.create_icon("C:\\Dev\\frigate_icon.png")
        self.create_icon("C:\\Dev\\patrol_icon.png")
        self.create_icon("C:\\Dev\\rfa_icon.png")
        self.create_icon("C:\\Dev\\pac24_icon.png")
        self.create_icon("C:\\Dev\\submarine_icon.png")
        self.create_icon("C:\\Dev\\helicopter_icon.png")

        # Add icons to the units frame
        for icon in self.navy_icons:
            icon.pack(side=tk.LEFT, padx=10, pady=10)

    def create_icon(self, image_path):
        if os.path.exists(image_path):
            image = tk.PhotoImage(file=image_path)
            label = tk.Label(self.units_frame, image=image, bg="white")
            label.image = image
            label.bind("<ButtonPress-1>", self.on_icon_press)
            label.bind("<B1-Motion>", self.on_icon_drag)
            self.navy_icons.append(label)
        else:
            print(f"Image file not found: {image_path}")

    def on_icon_press(self, event):
        widget = event.widget
        widget.start_x = event.x
        widget.start_y = event.y

    def on_icon_drag(self, event):
        widget = event.widget
        x = widget.winfo_x() - widget.start_x + event.x
        y = widget.winfo_y() - widget.start_y + event.y
        widget.place(x=x, y=y)


if __name__ == "__main__":
    maritime = Maritime()
    maritime.mainloop()
